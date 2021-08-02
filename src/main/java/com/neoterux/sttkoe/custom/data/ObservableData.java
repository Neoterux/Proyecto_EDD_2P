package com.neoterux.sttkoe.custom.data;

import java.util.ArrayList;

/**
 * <h1>Observable Data</h1>
 * This is a container class that would hold a specified data and would emmit the changes to
 * its observers.
 *
 * @param <T> the type that this class holds
 */
public class ObservableData<T> extends ReadOnlyObservableData<T> {

    /**
     * Creates a new Observable Data object with an initial value.
     *
     * @param initialValue the initial value of the container.
     */
    public ObservableData(T initialValue) {
        super(initialValue);
        this.value = initialValue;
        observers = new ArrayList<>();
    }

    /**
     * Set the new value to the container, and emmit this change to all the observers.
     *
     * @param value the new value to set.
     */
    public synchronized void set(T value) {
        this.emmit(this.value, value);
        this.value = value;
    }

}
