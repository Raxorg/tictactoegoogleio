package com.frontanilla.firebase;

import com.frontanilla.tictactoe.TicTacToe;

/**
 * Created by Luis on 03/06/2017.
 */

public class SetterManager {

    private static SetterManager instance = new SetterManager();

    private TicTacToe game;
    private FirebaseInterface firebaseInterface;

    private SetterManager() {

    }

    public static SetterManager getInstance() {
        return instance;
    }

    public void setGame(TicTacToe game) {
        this.game = game;
    }

    public TicTacToe getGame() {
        return game;
    }

    public void setFirebaseInterface(FirebaseInterface firebaseInterface) {
        this.firebaseInterface = firebaseInterface;
    }

    public void boardUpdateFromDB(String board) {
        game.setBoard(board);
    }

    // We asked the DB whose turn it was, here's the answer
    public void gotTurnFromCheck(boolean playerX) {
        if (playerX == game.isPlayerX()) {
            game.playPiece(2);
        } else {
            game.playPiece(3);
        }
    }

    public void updateBoard(String board) {
        firebaseInterface.setBoard(board);
    }

    public void gotBoardFromUpdateConfirmed(String board) {
        game.setBoard(board);
        game.playPiece(4);
    }

    public void gotBoardFromEnteringApp(String board) {
        game.setBoard(board);
    }

    public void gotTurnFromEnteringApp(boolean turn) {
        game.setCurrentTurn(turn);
        game.setLoading(false);
    }

    public void gotTurnFromUpdateConfirmed(boolean turn) {
        game.playPiece(5);
    }

    //-----------------
    //  ASYNC UPDATES
    //-----------------

    public void asyncBoardUpdateFromDB(String board) {
        game.setBoard(board);
    }

    public void asyncTurnUpdateFromDB(boolean turn) {
        game.setCurrentTurn(turn);
    }
}
