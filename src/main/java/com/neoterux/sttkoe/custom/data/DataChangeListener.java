package com.neoterux.sttkoe.custom.data;

@FunctionalInterface
public interface DataChangeListener<T> {
    void doOnChange(T oldValue, T newValue);
}
