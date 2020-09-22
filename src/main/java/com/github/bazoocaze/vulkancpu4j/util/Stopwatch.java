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

public class Stopwatch {

    private static final double NANOS_IN_MILLI = 1000000.0;

    private long startTime;
    private boolean running;
    private long accumulated;
    private final NanoClock nanoClock;

    public Stopwatch() {
        nanoClock = System::nanoTime;
    }

    public Stopwatch(NanoClock nanoClock) {
        this.nanoClock = nanoClock;
    }

    public void start() {
        this.startTime = currentTime();
        this.running = true;
    }

    private long currentTime() {
        return nanoClock.getNanos();
    }

    public void stop() {
        if (this.running) {
            long stopTime = currentTime();
            this.accumulated += (stopTime - this.startTime);
            this.running = false;
        }
    }

    private long currentAccumulated() {
        return this.accumulated
                + ((this.running) ? (currentTime() - this.startTime) : 0);
    }

    public Elapsed elapsed() {
        return new Elapsed(currentAccumulated());
    }

    public double elapsedMilliseconds() {
        return currentAccumulated() / NANOS_IN_MILLI;
    }

    public void reset() {
        stop();
        this.accumulated = 0;
    }

    public void restart() {
        stop();
        this.accumulated = 0;
        start();
    }

    public static class Elapsed {

        public static final double NANOS_IN_SEC = 1000000000.0;

        public final long totalNanos;
        public final double totalSeconds;
        public final double totalMilliseconds;

        public Elapsed(long nanos) {
            this.totalNanos = nanos;
            this.totalSeconds = totalNanos / NANOS_IN_SEC;
            this.totalMilliseconds = totalNanos / NANOS_IN_MILLI;
        }
    }
}
