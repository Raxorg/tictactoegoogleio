package com.frontanilla.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frontanilla.helpers.Metrics;

/**
 * Created by Luis on 03/06/2017.
 */

class GameScreen extends ScreenAdapter {

    private SpriteBatch batch;
    private Texture e, x, o, color;
    private TicTacToe game;

    GameScreen(TicTacToe game) {
        batch = new SpriteBatch();
        e = new Texture("e.png");
        x = new Texture("x.png");
        o = new Texture("o.png");
        color = new Texture("color.png");

        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        renderColor();
        renderBoard();
        batch.end();
    }

    private void renderColor() {
        if (game.isPlayerX()) {
            batch.setColor(1, 216f / 255f, 0, 1);
        } else {
            batch.setColor(0, 19f / 255f, 127f / 255f, 1);
        }
        batch.draw(
                color,
                0,
                0,
                Gdx.graphics.getWidth()/2,
                Gdx.graphics.getHeight()
        );
        if (game.getCurrentTurn()) {
            batch.setColor(1, 216f / 255f, 0, 1);
        } else {
            batch.setColor(0, 19f / 255f, 127f / 255f, 1);
        }
        batch.draw(
                color,
                Gdx.graphics.getWidth()/2,
                0,
                Gdx.graphics.getWidth()/2,
                Gdx.graphics.getHeight()
        );
        batch.setColor(Color.WHITE);
    }

    private void renderBoard() {
        Texture piece = null;
        for (int a = 0; a < 9; a++) {
            switch (game.getBoard().charAt(a)) {
                case 'e':
                    piece = e;
                    break;
                case 'x':
                    piece = x;
                    break;
                case 'o':
                    piece = o;
                    break;
            }
            batch.draw(
                    piece,
                    Metrics.boardXPos + Metrics.cellSize * (a % 3),
                    Metrics.cellSize * (a / 3),
                    Metrics.cellSize,
                    Metrics.cellSize
            );
        }
    }
}
