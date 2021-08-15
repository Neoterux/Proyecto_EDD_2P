package com.neoterux.sttkoe.utils;

@FunctionalInterface
public interface TriConsumer<T, V, W>{
    void accept(T value, V value2, W value3);
}
