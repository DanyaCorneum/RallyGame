package io.github.RallyGameAlpha.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.RallyGameAlpha.RallyGame;
import io.github.RallyGameAlpha.entities.Background;
import io.github.RallyGameAlpha.utils.ScoreReader;
import io.github.RallyGameAlpha.utils.ScoreWriter;

public class TableRecordsScreen implements Screen {
    final RallyGame game;

    int currentScore;
    Array<Integer> currentTable;
    Texture background;


    public TableRecordsScreen(RallyGame game, Integer currentScore) {
        ScoreWriter.write(currentScore);
        String[] s = ScoreReader.read("data/tableOfRecords.txt");
        this.currentTable = new Array<>();
        for (String i : s) {
            currentTable.add(Integer.valueOf(i));
        }
        currentTable.add(currentScore);
        currentTable.sort();
        currentTable.reverse();
        this.currentScore = currentScore;
        this.game = game;
        this.background = new Texture("background/win.png");
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();

        game.batch.begin();
        game.batch.draw(background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
        game.font.draw(game.batch, "Your score \n " + this.currentScore + "\n", 560, 700);
        for (int i = 0; i <= currentTable.size - 1 && i <= 15; i++) {
            if (i < 7) {
                game.font.draw(game.batch, i + 1 + " " + " " + currentTable.get(i) + "\n", 360, 600 - i * 50);
            } else {
                game.font.draw(game.batch, i + 1 + " " + " " + currentTable.get(i) + "\n", 660, 600 - (i-7) * 50);
            }
        }
        game.font.draw(game.batch, "Press escape for exit", 500, 100);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
