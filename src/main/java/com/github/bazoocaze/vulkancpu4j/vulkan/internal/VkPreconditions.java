package com.github.bazoocaze.vulkancpu4j.vulkan.internal;

public class VkPreconditions {

    private VkPreconditions() {
        // static class
    }

    /**
     * Throw a {@link ArgumentNullException} if input is null.
     *
     * @param input     input to test
     * @param paramName parameter name for error message
     * @throws ArgumentNullException if input is null
     */
    public static void checkNull(Object input, String paramName) {
        if (input == null) {
            String message = String.format("Parameter [%s] cannot be null", paramName);
            throw new ArgumentNullException(message);
        }
    }

    /**
     * Throw a {@link IllegalArgumentException} if input string is null or blank (empty or only made of spaces).
     *
     * @param input     input to test
     * @param paramName parameter name for error message
     * @throws IllegalArgumentException if string is null or blank
     */
    public static void checkString(String input, String paramName) {
        if (input == null || input.isBlank()) {
            String message = String.format("Parameter [%s] cannot be null or blank string", paramName);
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Throw a {@link ArgumentOutOfRangeException} if input is out of range.
     *
     * @param input     input to test
     * @param min       min valid value for input
     * @param max       max valid value for input
     * @param paramName parameter name for error message
     * @throws ArgumentOutOfRangeException if input is out of range
     */
    public static void checkRange(int input, int min, int max, String paramName) {
        if (input < min || input > max) {
            String message = String.format("Parameter [%s] is out of range (min=%d, max=%d)", paramName, min, max);
            throw new ArgumentOutOfRangeException(message);
        }
    }

    /**
     * Throw a {@link InvalidOperationException} if input condition is {@link Boolean#TRUE}.
     *
     * @param throwCondition throw condition
     * @param message        error message
     * @throws InvalidOperationException
     */
    public static void checkOperation(Boolean throwCondition, String message) {
        if (Boolean.TRUE.equals(throwCondition)) {
            throw new InvalidOperationException(message);
        }
    }

    public static class ArgumentNullException extends IllegalArgumentException {

        public ArgumentNullException(String message) {
            super(message);
        }
    }

    private static class ArgumentOutOfRangeException extends IllegalArgumentException {

        public ArgumentOutOfRangeException(String message) {
            super(message);
        }
    }

    private static class InvalidOperationException extends UnsupportedOperationException {

        public InvalidOperationException(String message) {
            super(message);
        }
    }
}
