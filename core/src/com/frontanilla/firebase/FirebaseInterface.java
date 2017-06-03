package com.frontanilla.firebase;

/**
 * Created by Luis on 03/06/2017.
 */

public interface FirebaseInterface {

    //-----------------
    //     SETTERS
    //-----------------

    void setTurn(boolean playerX);

    void setBoard(String board);

    //-----------------
    //     GETTERS
    //-----------------

    void getTurnToCheck();

    void getBoardFromEnteringApp();

    void getTurnFromEnteringApp();
}
