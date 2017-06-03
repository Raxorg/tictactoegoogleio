package com.frontanilla.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.frontanilla.tictactoe.TicTacToe;

/**
 * Created by Luis on 03/06/2017.
 */

public class Input extends InputAdapter {

    private TicTacToe game;
    private int lastPlaceTouched;

    public Input(TicTacToe game) {
        this.game = game;
    }

    public int getLastPlaceTouched() {
        return lastPlaceTouched;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenY = Gdx.graphics.getHeight() - screenY;
        if (pointer == 0) {
            if (screenX > Metrics.boardXPos && screenX < Metrics.boardXPos + Metrics.boardSize) {
                calculatePlace(screenX, screenY);
                game.playPiece(1);
            } else {
                System.out.println("Attempting to change color");
                if (screenX < Metrics.boardXPos) {
                    game.setPlayerX(true);
                } else {
                    game.setPlayerX(false);
                }
            }
        }
        return true;
    }

    private void calculatePlace(int x, int y) {
        int place;
        x = (int) (x - Metrics.boardXPos);
        if (x < Metrics.boardSize / 3) {
            place = 0;
        } else if (x < (Metrics.boardSize / 3 * 2)) {
            place = 1;
        } else {
            place = 2;
        }
        if (y < Metrics.boardSize / 3) {
            place += 0;
        } else if (y < (Metrics.boardSize / 3) * 2) {
            place += 3;
        } else {
            place += 6;
        }
        System.out.println(place);
        this.lastPlaceTouched = place;
    }
}
