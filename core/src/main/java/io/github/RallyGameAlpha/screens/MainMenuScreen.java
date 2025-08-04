package io.github.RallyGameAlpha.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.RallyGameAlpha.RallyGame;
import io.github.RallyGameAlpha.utils.AppConfig;

public class MainMenuScreen implements Screen {
    private final Stage stage;
    Image background;
    AppConfig config;
    TextButton start;

    final RallyGame game;
    Sound click;


    public MainMenuScreen(RallyGame game) {
        this.game = game;
        this.config = new AppConfig();
        this.background = new Image(new Texture(Gdx.files.internal("mainMenu.jpg")));
        background.setFillParent(true);
        game.font.setColor(Color.WHITE);
        stage = new Stage(game.viewport);
        this.click = Gdx.audio.newSound(Gdx.files.internal("sounds/button.ogg"));

        initGUI();
    }

    public void initGUI() {
        Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        final Label title = new Label("Rally game", skin);

        final Label exit = new Label("Press Esc for exit", skin);
        title.setFontScale(2);
        title.setAlignment(5);
        title.setColor(Color.YELLOW);
        exit.setFontScale(2);
        exit.setAlignment(5);
        exit.setColor(Color.YELLOW);

        this.start = new TextButton("Start", skin, "default");
        final TextButton tutorial = new TextButton("Tutorial", skin, "default");
        final TextButton tableOfRecords = new TextButton("Table of records", skin, "default");
        final TextButton settings = new TextButton("Settings", skin, "default");

        start.getLabel().setFontScale(2);
        tutorial.getLabel().setFontScale(2);
        tableOfRecords.getLabel().setFontScale(2);
        settings.getLabel().setFontScale(2);


        start.getColor().set(Color.GOLD);
        tutorial.getColor().set(Color.GOLD);
        tableOfRecords.getColor().set(Color.GOLD);
        settings.getColor().set(Color.GOLD);
        tutorial.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (config.getSound().equals("on")) {
                    click.play();
                }
                game.setScreen(new TutorialScreen(game));
                dispose();
            }
        });
        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (config.getSound().equals("on")) {
                    click.play();
                }
                game.music.stop();
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });
        tableOfRecords.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (config.getSound().equals("on")) {
                    click.play();
                }
                game.setScreen(new TableRecordsScreen(game, 0));
                dispose();
            }
        });
        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (config.getSound().equals("on")) {
                    click.play();
                }
                game.setScreen(new SettingsScreen(game));
                dispose();
            }
        });

        stage.addActor(title);
        stage.addActor(exit);
        stage.addActor(background);
        stage.addActor(start);
        stage.addActor(tutorial);
        stage.addActor(tableOfRecords);
        stage.addActor(settings);
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        //кнопки

        table.add(title).padBottom(20f).width(400).height(100);
        table.row();

        table.add(start).padBottom(20f).width(400).height(100);
        table.row();

        table.add(tutorial).padBottom(20f).width(400).height(100);
        table.row();

        table.add(tableOfRecords).padBottom(20f).width(400).height(100);
        table.row();

        table.add(settings).padBottom(20f).width(400).height(100);
        table.row();
        table.add(exit).padBottom(20f).width(400).height(100);
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
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
        stage.dispose();
    }
}
