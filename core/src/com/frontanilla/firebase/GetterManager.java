package com.frontanilla.firebase;

/**
 * Created by Luis on 03/06/2017.
 */

public class GetterManager {

    private static GetterManager instance = new GetterManager();

    private FirebaseInterface firebaseInterface;

    private GetterManager() {

    }

    public static GetterManager getInstance() {
        return instance;
    }

    public void setFirebaseInterface(FirebaseInterface firebaseInterface) {
        this.firebaseInterface = firebaseInterface;
    }

    public void getTurnToCheck() {
        firebaseInterface.getTurnToCheck();
    }

    public void getBoardAtAppStart() {
        firebaseInterface.getBoardFromEnteringApp();
    }

    public void getTurnAtAppStart() {
        firebaseInterface.getTurnFromEnteringApp();
    }
}
