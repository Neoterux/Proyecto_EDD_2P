package com.neoterux.sttkoe;

/**
 * <h1>Launcher</h1>
 * This class is a wrapper for {@link App} that would act as a main class
 * for the jar file, to prevent JavaFx errors when launching application.
 */
public class Launcher {
    public static void main(String[] args) {
        App.main(args);
    }
}
