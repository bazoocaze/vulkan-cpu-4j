/*
MIT License
Copyright (c) 2020 J Ferreira (Bazoocaze)
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package com.github.bazoocaze.vulkancpu4j.util;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MyBenchmark {

    private static final int PASS_WARMUP = 0;
    private static final int PASS_PROPER = 2;
    private static final int MAX_PADDING = 50;
    private static final Action EMPTY_ACTION = () -> {
    };
    public static final int MINIMUM_WORK_TIME_MS = 500;
    public static final int NANOS_IN_SECOND = 1000000000;

    private static int benchNumber = 0;

    public static Builder aNewBenchmark() {
        return new Builder();
    }

    private static double runAction(Action action, int workSize) {
        Stopwatch timer = new Stopwatch();
        timer.start();
        UncheckedExceptions.unchecked(() -> {
            for (int p = 0; p < workSize; p++) {
                action.run();
            }
        });
        timer.stop();
        return fractionalSeconds(timer) / workSize;
    }

    private static double fractionalSeconds(Stopwatch timer) {
        return (double) timer.elapsed().toNanos() / (double) NANOS_IN_SECOND;
    }

    private static double runAction(Action action, int workSize, int minMilliseconds) {
        double cumulative = 0;
        int countTimerRepetitions = 0;
        Stopwatch repetitionsTimer = new Stopwatch();
        repetitionsTimer.start();
        do {
            cumulative += runAction(action, workSize);
            countTimerRepetitions++;
        } while (repetitionsTimer.elapsedMilliseconds() < minMilliseconds);

        return cumulative / countTimerRepetitions;
    }

    public static void run(String title, Action action, int workSize, OutputStream output) {
        run(Collections.singletonList(new BenchmarkItem(title, action)), workSize, title, output);
    }

    public static void run(List<BenchmarkItem> inputActions, int workSize, String title, OutputStream outputStream) {
        List<BenchmarkItem> benchmarks = formatDescriptions(inputActions);
        double emptyDelegateTime;

        if (title == null) {
            title = String.format("Benchmark %d", ++benchNumber);
        }

        PrintStream output = new PrintStream(outputStream);

        output.format("----- BEGIN BENCHMARK: %s -----%n", title);

        for (int numPass = 0; numPass <= PASS_PROPER; numPass++) {
            // Measure loop overhead
            emptyDelegateTime = runAction(EMPTY_ACTION, workSize, MINIMUM_WORK_TIME_MS);

            if (numPass == PASS_PROPER) {
                output.format("Loop overhead: time=%s%n", TimeFormatter.format(emptyDelegateTime));
            }

            for (BenchmarkItem benchmarkItem : benchmarks) {
                Action workDelegate = benchmarkItem.action();
                String workDescription = benchmarkItem.description();
                double workTime = 0;
                boolean error = false;
                String errorDescription = null;

                try {
                    workTime = runAction(workDelegate, workSize, MINIMUM_WORK_TIME_MS);
                } catch (Exception ex) {
                    error = true;
                    errorDescription = ex.getClass().getSimpleName();
                }

                if (numPass == PASS_PROPER) {
                    if (error) {
                        output.format("%s: FAILED[%s]%n", workDescription, errorDescription);
                    } else {
                        double corrected = workTime - emptyDelegateTime;
                        if (workTime > (1000 * emptyDelegateTime)) {
                            output.format("%s: time=%s%n", workDescription, TimeFormatter.format(workTime));
                        } else {
                            output.format("%s: time=%s corrected=%s%n", workDescription,
                                    TimeFormatter.format(workTime), TimeFormatter.format(corrected));
                        }
                    }
                }
            }
        }

        output.format("----- END BENCHMARK: %s -----%n", title);
    }

    private static List<BenchmarkItem> formatDescriptions(List<BenchmarkItem> inputActions) {
        int size = inputActions.stream().map(i -> i.description.length()).max(Integer::compareTo).orElse(MAX_PADDING);
        int padDesc = Math.min(MAX_PADDING, size);
        return inputActions.stream()
                .map(i -> new BenchmarkItem(pad(i.description, padDesc), i.action))
                .collect(Collectors.toList());
    }

    private static String pad(String input, int size) {
        return input + " ".repeat(Math.max(0, size - input.length()));
    }

    @FunctionalInterface
    public interface Action {

        void run() throws Exception;
    }

    public static class BenchmarkItem {

        private final String description;
        private final Action action;

        public BenchmarkItem(String description, Action action) {
            this.description = description;
            this.action = action;
        }

        public String description() {
            return description;
        }

        public Action action() {
            return action;
        }
    }

    public static class Builder {

        private final List<BenchmarkItem> actions = new ArrayList<>();
        private int workSize = 1000;
        private String title = "";
        private OutputStream outputStream = System.out;

        public Builder withAction(String description, Action action) {
            this.actions.add(new BenchmarkItem(description, action));
            return this;
        }

        public Builder withWorkSize(int workSize) {
            this.workSize = workSize;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withOutput(OutputStream outputStream) {
            this.outputStream = outputStream;
            return this;
        }

        public void run() {
            MyBenchmark.run(actions, this.workSize, this.title, this.outputStream);
        }
    }
}
