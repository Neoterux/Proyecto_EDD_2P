package com.neoterux.sttkoe.custom.data;

import java.util.ArrayList;

/**
 * <h1>Read Only Observable Data</h1>
 * This class only contains a sub capability of {@link ObservableData}.
 *
 * @param <T> the type that this class holds
 */
public abstract class ReadOnlyObservableData<T> {

    /**
     * The value that this Observable holds.
     */
    protected T value;

    /**
     * The list of observers
     */
    protected ArrayList<DataChangeListener<T>> observers;

    public ReadOnlyObservableData(T initialValue) {
        this.value = initialValue;
        observers = new ArrayList<>();
    }


    /**
     * Subscribe to be able to listen the changes into the object.
     *
     * @param subscriber the subscriber that would listen the changes.
     */
    public synchronized void subscribe(DataChangeListener<T> subscriber) {
        if (subscriber != null)
            observers.add(subscriber);
    }

    /**
     * Remove a previus inserted listener only if exists
     *
     * @param listener the listener to remove
     */
    public synchronized void unsubscribe(DataChangeListener<T> listener) {
        if (listener != null)
            observers.remove(listener);
    }

    /**
     * Retrieves the value of the observable.
     *
     * @return the current value of the data.
     */
    public synchronized T get() {
        return this.value;
    }

    protected synchronized void emmit(T oldValue, T value) {
        observers.forEach(it -> it.doOnChange(oldValue, value));
    }
}
