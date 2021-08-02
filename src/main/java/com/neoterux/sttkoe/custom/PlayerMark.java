package com.neoterux.sttkoe.custom;

import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeLineJoin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>PlayerMark</h1>
 * <p>
 * This class is a shape that represent the mark of a player in the tik tak toe game.
 */
public abstract class PlayerMark extends SVGPath implements Serializable {

    private final String name;

    private static final long serialVersionUID = 83949210923483L;

    private static final String MARK_PATH = "game/marks";

    /**
     * Only can be
     */
    private PlayerMark(String name) {
        this.name = name;
        configure();
    }


    public static final PlayerMark CROSS = new CrossMark();

    public static final PlayerMark CIRCLE = new CircleMark();

    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Read custom marks that would be stored in the mark folder.
     *
     * @return a list with the custom marks that could be stored into the mark folder.
     */
    public static List<PlayerMark> readCustoms() {
        // TODO: Read marks in folder
        List<PlayerMark> out = new ArrayList<>();


        return out;
    }

    /**
     * Saves the mark in the
     */
    public void write() {

    }

    public String getName() {
        return this.name;
    }

    /**
     * Configures the shape of the Mark
     */
    protected abstract void configure();


    private static final class CrossMark extends PlayerMark {
        public CrossMark() {
            super("Cross");
            getStyleClass().add("cross-mark");
            setContent("M0 0 3 0 5 3 7 0 10 0 7 5 10 10 7 10 5 7 3 10 0 10 3 5 0 0");
            strokeLineJoinProperty().set(StrokeLineJoin.ROUND);


        }

        @Override
        protected void configure() {
            strokeLineJoinProperty().set(StrokeLineJoin.ROUND);


        }
    }

    private static final class CircleMark extends PlayerMark {
        public CircleMark() {
            super("Circle");
            getStyleClass().add("circle-mark");
            setContent("m5 0a1 1 0 010 10a1 1 0 010-10m0 2a1 1 0 000 6a1 1 0 000-6");


        }

        @Override
        protected void configure() {

        }
    }

    private static final class CustomMark extends PlayerMark {
        public CustomMark(String name) {
            super(name);
            getStyleClass().add("custom-mark");
        }

        @Override
        protected void configure() {

        }
    }

}
