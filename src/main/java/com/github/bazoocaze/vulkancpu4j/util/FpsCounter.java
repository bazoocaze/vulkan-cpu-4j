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

import java.io.PrintStream;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public class FpsCounter {

    private static final double MIN_TIME_MS = 0.000001;
    private static final long DEFAULT_REPORT_TIME_MS = 5000;

    private final String tag;
    private final long reportTime;
    private final Set<FpsReportCounters> reportCounters;

    private final Stopwatch cumulative;
    private final Stopwatch elapsed;
    private final PrintStream output;

    private int frameCounter;

    public FpsCounter(String tag, long reportTime, Set<FpsReportCounters> reportCounters, PrintStream output) {
        this.tag = tag;
        this.reportTime = reportTime;
        this.reportCounters = reportCounters;
        this.output = output;

        cumulative = new Stopwatch();
        elapsed = new Stopwatch();

        // START
        frameCounter = 0;
        elapsed.start();
    }

    public static Builder aFpsCounter() {
        return new Builder();
    }

    public void begin() {
        frameCounter++;
        cumulative.start();
    }

    public void end() {
        cumulative.stop();
    }

    private void generateReport() {
        double cumulativeMs = cumulative.elapsed().toMillis();
        double elapsedMs = elapsed.elapsed().toMillis();
        double frames = Math.max(frameCounter, 1);

        cumulativeMs = Math.max(cumulativeMs, MIN_TIME_MS);
        elapsedMs = Math.max(elapsedMs, DEFAULT_REPORT_TIME_MS);

        double fps = frames * 1000f / elapsedMs;
        double perc = cumulativeMs / elapsedMs * 100;
        double frameTimeMs = cumulativeMs / frames;

        output.format("FPS(%s):", tag);

        if (reportCounters.contains(FpsReportCounters.FPS)) {
            output.format(" fps=%1.2f", fps);
        }

        if (reportCounters.contains(FpsReportCounters.FRAMES)) {
            output.format(" frames=%1.0f", frames);
        }

        if (reportCounters.contains(FpsReportCounters.CUMULATIVE_TIME)) {
            output.format(" time=%s", TimeFormatter.format(cumulativeMs / 1000f));
        }

        if (reportCounters.contains(FpsReportCounters.ELAPSED_TIME)) {
            output.format(" ellapsed=%s", TimeFormatter.format(elapsedMs / 1000f));
        }

        if (reportCounters.contains(FpsReportCounters.PERCENT_CUMULATIVE)) {
            output.format(" perc=%1.2f%%", perc);
        }

        if (reportCounters.contains(FpsReportCounters.FRAME_TIME)) {
            output.format(" frame_time=%s", TimeFormatter.format(frameTimeMs / 1000f));
        }

        output.println();
    }

    public void report() {
        generateReport();

        cumulative.reset();
        frameCounter = 0;
        elapsed.restart();
    }

    public void periodicReport() {
        if (elapsed.elapsedMilliseconds() < reportTime)
            return;

        report();
    }

    public enum FpsReportCounters {
        FPS,
        FRAMES,
        CUMULATIVE_TIME,
        ELAPSED_TIME,
        PERCENT_CUMULATIVE,
        FRAME_TIME;

        public static final Set<FpsReportCounters> DefaultCounters =
                Collections.unmodifiableSet(EnumSet.of(FPS, FRAMES, CUMULATIVE_TIME, PERCENT_CUMULATIVE));
        public static final Set<FpsReportCounters> SimpleFrameTime =
                Collections.unmodifiableSet(EnumSet.of(FRAME_TIME, PERCENT_CUMULATIVE));
        public static final Set<FpsReportCounters> AllCounters =
                Collections.unmodifiableSet(EnumSet.allOf(FpsReportCounters.class));
    }

    public static class Builder {

        private final Set<FpsReportCounters> reportCounters = EnumSet.copyOf(FpsReportCounters.DefaultCounters);
        private String tag = "FpsCounter";
        private long reportTime = DEFAULT_REPORT_TIME_MS;
        private PrintStream output = System.out;

        public Builder withTag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder withReportTime(long reportTime) {
            this.reportTime = reportTime;
            return this;
        }

        public Builder with(FpsReportCounters counter) {
            this.reportCounters.add(counter);
            return this;
        }

        public Builder with(Set<FpsReportCounters> counter) {
            this.reportCounters.clear();
            this.reportCounters.addAll(counter);
            return this;
        }

        public Builder withOutput(PrintStream output) {
            this.output = output;
            return this;
        }

        public FpsCounter create() {
            return new FpsCounter(tag, reportTime, reportCounters, output);
        }
    }
}
