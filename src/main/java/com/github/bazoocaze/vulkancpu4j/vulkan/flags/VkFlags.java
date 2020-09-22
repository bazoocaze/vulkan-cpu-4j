package com.github.bazoocaze.vulkancpu4j.vulkan.flags;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.StringJoiner;

public class VkFlags<T extends Enum<T>> {

    private final Class<T> bitFlagsClass;
    private final EnumSet<T> value;

    public VkFlags(Class<T> bitFlagsClass, T[] flags) {
        this.bitFlagsClass = bitFlagsClass;
        this.value = EnumSet.noneOf(bitFlagsClass);
        this.value.addAll(Arrays.asList(flags));
    }

    public boolean contains(T flag) {
        return value.contains(flag);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", VkFlags.class.getSimpleName() + "[", "]")
                .add("bitFlagsClass=" + bitFlagsClass.getSimpleName())
                .add("value=" + value)
                .toString();
    }
}
