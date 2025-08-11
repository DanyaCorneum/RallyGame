package io.github.RallyGameAlpha;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.RallyGameAlpha.screens.MainMenuScreen;
import io.github.RallyGameAlpha.utils.AppConfig;
public class RallyGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    public FitViewport viewport;

    AppConfig config;
    public String player;
    public Music music;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        viewport = new FitViewport(1280, 720);
        player = "Anonimus";

        font.setUseIntegerPositions(false);
        font.getData().setScale(2f);

        this.music = Gdx.audio.newMusic(
            Gdx.files.internal("sounds/main menu.mp3")
        );
        config = new AppConfig();
        if (config.getMusic().equals("on")) {
            music.setLooping(true);
            music.setVolume(.5f);
            music.play();
            Gdx.app.log("music", "music starts");

        }

        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
