package com.neoterux.sttkoe.custom.pane;

import com.neoterux.sttkoe.custom.data.DataChangeListener;
import com.neoterux.sttkoe.custom.data.ObservableData;
import com.neoterux.sttkoe.custom.data.ReadOnlyObservableData;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardPane<T> extends Pane {

    private static final Logger log = LoggerFactory.getLogger(BoardPane.class);

    private final ObservableData<Double> spacingHprop;
    private final ObservableData<Double> spacingVprop;

    private final T[][] boardContent;

    /**
     * The holders button that would hold the
     */
    private final Button[][] holders;

    /**
     * The action that would be executed when a item of type {@link T} is inserted.
     */
    private InsertAction<T> insertionMapper;


    /**
     * Create a new BoardPane that holds button with content of type {@link T}
     */
    @SuppressWarnings("unchecked")
    public BoardPane() {
        setStyle("-fx-border-width: 3px;" +
                "-fx-border-color: red;" +
                "-fx-shape: M0 3 3 3 3 4 0 4 0 3M3 0 3 11 4 11 4 0 3 0M7 0 7 11 8 11 8 0 7 0M0 7 3 7 3 8 0 8 0 7M4 3 7 3 7 4 4 4M8 3 11 3 11 4 8 4M4 7 7 7 7 8 4 8 4 7M8 7 11 7 11 8 8 8 8 7;");
        setPadding(new Insets(10));
        getChildren().add(new Label("Hola mundo"));
        getStyleClass().add("board-pane");
        this.insertionMapper = null;
        boardContent = (T[][]) new Object[3][3];
        holders = new Button[3][3];
        spacingHprop = new ObservableData<>(0.0);
        spacingVprop = new ObservableData<>(0.0);
        initHolders();

        var a = new DataChangeListener<Double>() {
            @Override
            public void doOnChange(Double oldValue, Double newValue) {
                log.debug("this change, " + oldValue + " to " + newValue);
            }
        };
        spacingVprop.subscribe(a);
        spacingHprop.subscribe(a);
        configureSizeObserver();
    }


    public ReadOnlyObservableData<Double> getHorizontalSpacingProperty() {
        return this.spacingHprop;
    }

    public ReadOnlyObservableData<Double> getVerticalSpacingProperty() {
        return this.spacingVprop;
    }

    public void setOnInsertionMapper(InsertAction<T> mapper) {
        this.insertionMapper = mapper;
    }

    private void initHolders() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button b = new Button("");
                b.getStyleClass().add("holder-button");
                holders[i][j] = b;
            }
        }
    }


    private void configureSizeObserver() {
        widthProperty().addListener(((observable, oldValue, newValue) ->
                spacingHprop.set(newValue.doubleValue() / 3.0)
        ));
        heightProperty().addListener(((observable, oldValue, newValue) ->
                spacingVprop.set(newValue.doubleValue() / 3.0)
        ));
    }


    public interface InsertAction<T> {

        void doOnInsertion(final Button holder, final T object);
    }
}
