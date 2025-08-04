package io.github.RallyGameAlpha.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.RallyGameAlpha.RallyGame;
import io.github.RallyGameAlpha.utils.AppConfig;
import org.w3c.dom.Text;

public class SettingsScreen implements Screen {
    final RallyGame game;
    private final Stage stage;
    private final AppConfig config;
    Sound click;


    public SettingsScreen(RallyGame game) {
        this.game = game;
        this.stage = new Stage(game.viewport);
        this.config = new AppConfig();
        this.click = Gdx.audio.newSound(Gdx.files.internal("sounds/button.ogg"));
        initGUI();
    }


    public void initGUI() {
        Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        final Label title = new Label("Settings", skin);
        final TextButton screen = new TextButton("FullScreen: " + (
            config.getFullScreen().equals("true") ? "off" : "on"), skin, "default");
        final TextButton sound = new TextButton("Sound: " + config.getSound(), skin, "default");
        final TextButton music = new TextButton("Music: " + config.getMusic(), skin, "default");
        final TextButton difficulty = new TextButton("Difficulty: " + config.getDifficulty(), skin, "default");
        final Label text = new Label("Press esc to exit", skin);

        screen.getLabel().setFontScale(2);
        sound.getLabel().setFontScale(2);
        music.getLabel().setFontScale(2);
        difficulty.getLabel().setFontScale(2);

        text.setAlignment(5);
        title.setAlignment(5);
        title.setFontScale(2);
        screen.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (config.getFullScreen().equals("true")) {
                    if (config.getSound().equals("on")) {
                        click.play();
                    }
                    config.setFullScreen("false");
                    screen.setText("FullScreen: on");
                    Gdx.graphics.setWindowedMode(800, 500);
                } else {
                    if (config.getSound().equals("on")) {
                        click.play();
                    }
                    Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
                    Gdx.graphics.setFullscreenMode(displayMode);
                    config.setFullScreen("true");
                    screen.setText("FullScreen: off");
                }
            }
        });
        sound.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (config.getSound().equals("on")) {
                    config.setSound("off");
                    sound.setText("Sound: " + config.getSound());
                    Gdx.app.log("sound", "sound is off");
                } else {
                    click.play();
                    config.setSound("on");
                    sound.setText("Sound: " + config.getSound());
                    Gdx.app.log("sound", "sound is on");
                }

            }
        });
        music.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (config.getMusic().equals("on")) {
                    if (config.getSound().equals("on")) {
                        click.play();
                    }
                    config.setMusic("off");
                    game.music.stop();
                    music.setText("Music: " + config.getMusic());
                    Gdx.app.log("music", "music is off");
                } else {
                    if (config.getSound().equals("on")) {
                        click.play();
                    }
                    config.setMusic("on");
                    game.music.play();
                    music.setText("Music: " + config.getMusic());
                    Gdx.app.log("music", "music is on");
                }
            }
        });
        difficulty.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (config.getDifficulty().equals("normal")) {
                    if (config.getSound().equals("on")) {
                        click.play();
                    }
                    config.setDifficulty("hard");
                    difficulty.setText("Difficulty: " + config.getDifficulty());
                    Gdx.app.log("difficulty", "hard");
                } else {
                    if (config.getSound().equals("on")) {
                        click.play();
                    }
                    config.setDifficulty("normal");
                    difficulty.setText("Difficulty: " + config.getDifficulty());
                    Gdx.app.log("difficulty", "normal");
                }
            }
        });

        stage.addActor(screen);
        stage.addActor(sound);
        stage.addActor(music);
        stage.addActor(difficulty);
        stage.addActor(text);
        stage.addActor(title);
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        //кнопки
        table.add(title).padBottom(20f).width(400).height(100);
        table.row();

        table.add(screen).padBottom(20f).width(400).height(100);
        table.row();

        table.add(sound).padBottom(20f).width(400).height(100);
        table.row();

        table.add(music).padBottom(20f).width(400).height(100);
        table.row();

        table.add(difficulty).padBottom(20f).width(400).height(100);
        table.row();

        table.add(text).padBottom(20f).width(400).height(100);
        table.row();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.batch.begin();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.setViewport(game.viewport);

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
