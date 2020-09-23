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

public class FpsControl {

    private final Stopwatch elapsed;
    private final double frameTime;

    private int frameCount;
    private double meanIdleTimeMs;
    private boolean enabled;

    public FpsControl() {
        this(30);
    }

    public FpsControl(int targetFps) {
        frameCount = 0;
        elapsed = new Stopwatch();
        elapsed.start();

        frameTime = 1000f / targetFps;

        setEnabled(true);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        updateState(enabled);
    }

    private void updateState(boolean enabled) {
        if (enabled == this.enabled)
            return;

        if (enabled) {
            elapsed.restart();
            frameCount = 0;
        } else {
            elapsed.stop();
        }

        this.enabled = enabled;
    }

    public void update() {
        int timeToSleep = 1;

        if (enabled) {
            frameCount++;
            var calculatedEllapsedMs = frameTime * frameCount;
            var timeDiff = calculatedEllapsedMs - elapsed.elapsedMilliseconds();

            testReset();

            if (timeDiff >= 1) {
                meanIdleTimeMs = (meanIdleTimeMs + timeDiff) / 2;
                timeToSleep = (int) Math.max(timeDiff, frameTime);
            }
        }

        threadSleep(timeToSleep);
    }

    private void threadSleep(int timeToSleep) {
        UncheckedException.unchecked(() -> Thread.sleep(timeToSleep));
    }

    private void reset() {
        elapsed.restart();
        frameCount = 0;
    }

    private void testReset() {
        if (elapsed.elapsedMilliseconds() > 1000)
            reset();
    }
}
