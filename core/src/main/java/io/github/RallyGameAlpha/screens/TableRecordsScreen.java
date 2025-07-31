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

public class TableRecordsScreen implements Screen {
    final RallyGame game;

    int currentScore;
    Array<Integer> tableScore;
    Texture background;


    public TableRecordsScreen(RallyGame game, Integer currentScore){
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
        game.font.draw(game.batch, "Your score " + this.currentScore,game.viewport.getWorldWidth()/2 , game.viewport.getWorldHeight()/2);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
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
