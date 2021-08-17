package com.neoterux.sttkoe.game.core;

import com.neoterux.sttkoe.custom.controls.GridButton;
import com.neoterux.sttkoe.game.core.listeners.GameValidationListener;
import com.neoterux.sttkoe.models.players.Player;
import com.neoterux.sttkoe.models.players.PlayerSequence;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.neoterux.sttkoe.utils.TableUtils.matrixIterFiller;

/**
 * This class has the responsibility to handler the game turn, and other aspects.
 */
@Slf4j(topic = "GameManager")
public class GameManager {

    private final PlayerSequence turn;

    /**
     * The game mode configuration of the current game
     */
    private final GameMode gameMode;

    /**
     * The grid of buttons of the game
     */
    private GridButton ttbuttons[][];
    
    /**
     * The ui controls handler object.
     */
    private GameControls ui;
    
    /**
     * The listener when an it's the turn of the AI.
     */
    private AiChangeDetectedListener aiListener;
    
    /**
     * The validation listener that react when a user win, loose or is a tie.
     */
    @Setter private GameValidationListener validationListener;
    
    private GameDeterminator determinator;
    
    public GameManager(GameMode mode){
        this.gameMode = mode;
        this.turn = new PlayerSequence(players());
        this.selectPlayer();
        this.turn.setChangeListener((newPlayer) -> {
            writeCurrentTurn();
            if (newPlayer.isCpu() && aiListener != null)
                aiListener.doOnChange(ui, turn.getCurrent());
        });
    }
    
    public void fillGrid(GridPane holder){
        log.debug("Filling the holder {}", holder);
        ttbuttons = new GridButton[3][3];
        fillMatrix((button, row, col)-> holder.add(button,col,row));
    }
    
    private void fillMatrix(FillAction<GridButton> action){
        log.debug("filling matrix...");
        matrixIterFiller(ttbuttons, (row, col) ->{
            GridButton button = new GridButton();
            button.fillAllGrid();
            button.setOnAction(event-> {
                log.debug("Button clicked on row: {}, col: {}", row, col);
                if (verifySelection(button)) {
                    validateState();
                    turn.nextPlayer();
                }
            });
            if (action!= null)
                action.doAction(button,row,col);
            return  button;
        });
        log.debug("End of filling");
        this.determinator = GameDeterminator.of(ttbuttons);
    }

    public void init(GameControls uiControls) {
        log.info("Initializing game settings");
        this.ui = uiControls;
    }
    
    private void validateState() {
        log.debug("Validating for player: {}", turn.getCurrent());
        if (validationListener != null){
            log.info("Validating...");
            if (determinator.validateFor(turn.getCurrent())) {
                validationListener.doOnWin(turn.getCurrent());
                return;
            }
            if (ui.tableIsFull())
                validationListener.doOnTie();
        }
        
    }
    
    private void writeCurrentTurn() {
        if (ui != null)
            ui.setCurrentPlayer(turn.getCurrent());
    }
    
    /**
     * Verify if the current button allow to place a new symbol.
     *
     * @param button the target button.
     * @return true if the button is allowed.
     */
    private boolean verifySelection(GridButton button) {
        if(button.isBlank()){
            Player currentPlayer = turn.getCurrent();
            button.setAsociatedPlayer(currentPlayer);
            button.setSymbol(currentPlayer.getPlayerSymbol());
            return true;
        }
        return false;
    }

    /**
     * Show the selection dialog for the beginning player
     */
    private void selectPlayer() {
        var players = Arrays.stream(players()).map(Player::getName).collect(Collectors.toList());
        Dialog<String> playerForm = new ChoiceDialog<>(null, players);
        playerForm.setResizable(true); // Fix stage too small on Kwin, don't delete :p
        playerForm.setContentText("Escoja quien comienza la partida.");
        playerForm.setTitle("Selector de jugador");
        String selected = playerForm.showAndWait().orElse(null);
        if (selected != null){
            if(!turn.getCurrent().getName().equals(selected))
                turn.nextPlayer();
            writeCurrentTurn();
        }else{
            log.info("The next exception is intended, not report");
            new Alert(Alert.AlertType.ERROR, "No se seleciono jugador", ButtonType.OK).showAndWait();
            throw new IllegalStateException("Selected player cannot be null");
        }
    }


    private Player[] players() {
        Player[] players = new Player[2];
        players[0] = gameMode.turnManager.getVisitorPlayer();
        players[1] = gameMode.turnManager.getHomePlayer();
        return players;
    }
    
    private interface FillAction<T>{
        void doAction(T content, int row, int col);
    }
    
    public void setAiListener (AiChangeDetectedListener aiListener) {
        this.aiListener = aiListener;
    }
    
    public interface AiChangeDetectedListener {
        void doOnChange(GameControls ui, Player ai);
    }
}
