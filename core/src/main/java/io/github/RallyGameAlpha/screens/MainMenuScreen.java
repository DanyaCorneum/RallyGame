package io.github.RallyGameAlpha.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.RallyGameAlpha.RallyGame;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class MainMenuScreen implements Screen {
    private Stage stage;
    private Table table;
    private Skin skin;
    Image background;

    final RallyGame game;

    public MainMenuScreen(RallyGame game) {
        this.game = game;
        this.background = new Image(new Texture(Gdx.files.internal("background/background1.png")));
        background.setFillParent(true);
        game.font.setColor(Color.WHITE);

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        stage = new Stage(game.viewport);
        final TextButton start = new TextButton("Start", skin, "default");
        final TextButton tutorial = new TextButton("Tutorial", skin, "default");
        final TextButton tableOfRecords = new TextButton("Table of records", skin, "default");
        tutorial.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new TutorialScreen(game));
                dispose();
            }
        });
        start.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });
        tableOfRecords.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new TableRecordsScreen(game, 0));
                dispose();
            }
        });

        stage.addActor(background);
        stage.addActor(start);
        stage.addActor(tutorial);
        stage.addActor(tableOfRecords);
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        //кнопки
        table.add(start).padBottom(20f).width(400).height(100);
        table.row();

        table.add(tutorial).padBottom(20f).width(400).height(100);
        table.row();

        table.add(tableOfRecords).padBottom(20f).width(400).height(100);
        table.row();
//        table.setDebug(true);

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
