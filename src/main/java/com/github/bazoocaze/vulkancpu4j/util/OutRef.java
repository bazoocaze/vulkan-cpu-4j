package com.github.bazoocaze.vulkancpu4j.util;

@FunctionalInterface
public interface OutRef<T> {

    void set(T value);
}
