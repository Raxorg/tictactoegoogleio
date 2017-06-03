package com.frontanilla.tictactoe;

import android.os.Bundle;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.frontanilla.firebase.SetterManager;

public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        TicTacToe game = new TicTacToe();

        SetterManager.getInstance().setGame(game);

        FirebaseConnection firebaseConnection = new FirebaseConnection();

        game.setFirebaseConnection(firebaseConnection);

        initialize(game, config);
    }
}
