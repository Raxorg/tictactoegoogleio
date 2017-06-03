package com.frontanilla.helpers;

import com.badlogic.gdx.Gdx;

/**
 * Created by Luis on 03/06/2017.
 */

public class Metrics {

    public static float boardSize, cellSize, boardXPos;

    public static void load() {
        boardSize = Gdx.graphics.getHeight();
        cellSize = boardSize / 3;
        boardXPos = Gdx.graphics.getWidth() / 2 - boardSize / 2;
    }
}
