package com.github.bazoocaze.vulkancpu4j.util;

public class ByRef<T> implements OutRef<T> {

    private T value;

    public ByRef() {
        this.value = null;
    }

    public ByRef(T value) {
        this.value = value;
    }

    public static <U> void setValue(ByRef<U> reference, U value) {
        if (reference != null) {
            reference.set(value);
        }
    }

    @Override
    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    @Override
    public String toString() {
        return "ByRef{value=" + value + "}";
    }

}
