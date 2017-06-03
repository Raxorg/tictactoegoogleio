package com.frontanilla.tictactoe;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.frontanilla.firebase.FirebaseInterface;
import com.frontanilla.firebase.GetterManager;
import com.frontanilla.firebase.SetterManager;
import com.frontanilla.helpers.Input;
import com.frontanilla.helpers.Metrics;

public class TicTacToe extends Game {

    // Attributes
    private String board = "eeeeeeeee";
    private boolean isPlayerX = true, loading = true;
    private boolean currentTurn = true;
    private Input input;


    public void setPlayerX(boolean playerX) {
        // Only let a player change its turn if it was not doing something else
        if (!loading) {
            this.isPlayerX = playerX;
        }
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public void setCurrentTurn(boolean currentTurn) {
        this.currentTurn = currentTurn;
    }

    public boolean getCurrentTurn() {
        return currentTurn;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public String getBoard() {
        return board;
    }

    public boolean isPlayerX() {
        return isPlayerX;
    }

    public void playPiece(int step) {
        if (validPlace()) {
            switch (step) {
                // Step 1: we touch the board and ask if it was our turn
                case 1:
                    if (!loading) {
                        System.out.println("Is it our turn?");
                        GetterManager.getInstance().getTurnToCheck();
                        loading = true;
                    }
                    break;
                // Called by FirebaseConnection
                case 2:
                    System.out.println("It was our turn, updating game");
                    SetterManager.getInstance().updateBoard(calculateNewBoard());
                    loading = true;
                    break;
                // Called by FirebaseConnection (Successful update)
                case 3:
                    System.out.println("Action denied, not our turn");
                    loading = false;
                    break;
                case 4:
                    System.out.println("Board update successful, wait turn update");
                    break;
                case 5:
                    System.out.println("Turn update successful");
                    loading = false;
                    break;
            }
        } else {
            loading = false;
        }
    }

    private boolean validPlace() {
        for (int a = 0; a < board.length(); a++) {
            if (a == input.getLastPlaceTouched()) {
                if (board.charAt(a) == 'e') {
                    System.out.println("Valid place");
                    return true;
                }
            }
        }
        return false;
    }

    private String calculateNewBoard() {
        char piece = isPlayerX ? 'x' : 'o';
        char[] boardCopy = board.toCharArray();
        for (int a = 0; a < boardCopy.length; a++) {
            if (a == input.getLastPlaceTouched()) {
                boardCopy[a] = piece;
            }
        }
        String result = "";
        for (int a = 0; a < boardCopy.length; a++) {
            result += boardCopy[a] + "";
        }
        return result;
    }

    @Override
    public void create() {
        Metrics.load();
        input = new Input(this);
        Gdx.input.setInputProcessor(input);
        setScreen(new GameScreen(this));
        // Request board update
        GetterManager.getInstance().getBoardAtAppStart();
        GetterManager.getInstance().getTurnAtAppStart();
    }

    void setFirebaseConnection(FirebaseInterface firebaseConnection) {
        GetterManager.getInstance().setFirebaseInterface(firebaseConnection);
        SetterManager.getInstance().setFirebaseInterface(firebaseConnection);
    }
}
