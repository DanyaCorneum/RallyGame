package io.github.RallyGameAlpha.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.RallyGameAlpha.RallyGame;
import io.github.RallyGameAlpha.utils.AppConfig;
import io.github.RallyGameAlpha.utils.ScoreReader;
import io.github.RallyGameAlpha.utils.ScoreWriter;

import java.util.*;

public class TableRecordsScreen implements Screen {
    final RallyGame game;

    Sound win;
    int currentScore;
    HashMap<String, Integer> currentTable;
    public List<Map.Entry<String, Integer>> entries;
    Texture background;


    public TableRecordsScreen(RallyGame game, Integer currentScore) {
        AppConfig config = new AppConfig();
        Gdx.app.log("gasdfd", String.valueOf(currentScore));
        this.win = Gdx.audio.newSound(Gdx.files.internal("sounds/finish.wav"));
        if (currentScore != 0) {
            ScoreWriter.write(currentScore, game);
            if (config.getMusic().equals("on")) {
                win.play();
            }
        }
        String[] s = ScoreReader.read("data/tableOfRecords.txt");
        this.currentTable = new HashMap<>();
        for (String i : s) {
            String key = i.split(" ")[0];
            Integer item = Integer.valueOf(i.split(" ")[1]);
            if (currentTable.containsKey(key)) {
                if (item > currentTable.get(key)) {
                    currentTable.put(key, item);
                }
            } else {
                currentTable.put(key, item);
            }
            Gdx.app.log("Score", i);
        }
        this.entries = new ArrayList<>(currentTable.entrySet());
        entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

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
        game.font.setColor(Color.WHITE);
        game.batch.draw(background, 0, 0, game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
        game.font.draw(game.batch, "Your score \n " + this.currentScore + "\n", 560, 700);
        for (int i = 0; i < entries.size() && i < 15; ++i) {
            if (i < 7) {
                game.font.draw(game.batch, i + 1 + " " + " " + entries.get(i).getKey() + " " + entries.get(i).getValue() + "\n", 360, 600 - i * 50);

            } else {
                game.font.draw(game.batch, i + 1 + " " + " " + entries.get(i).getKey() + " " + entries.get(i).getValue() + "\n", 660, 600 - (i - 7) * 50);
            }
        }
        game.font.draw(game.batch, "Press escape for exit", 500, 100);
        game.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
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
