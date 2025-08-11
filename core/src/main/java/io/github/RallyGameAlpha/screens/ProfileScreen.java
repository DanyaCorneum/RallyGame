package io.github.RallyGameAlpha.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.RallyGameAlpha.RallyGame;
import io.github.RallyGameAlpha.entities.Background;
import io.github.RallyGameAlpha.utils.AppConfig;

public class ProfileScreen implements Screen {
    final RallyGame game;
    AppConfig config;
    Sound click;
    Stage stage;
    Image background;

    public ProfileScreen(RallyGame game){
        this.game = game;
        this.config = new AppConfig();
        this.click = Gdx.audio.newSound(Gdx.files.internal("sounds/button.ogg"));
        stage = new Stage(game.viewport);
        this.background = new Image(new Texture(Gdx.files.internal("mainMenu.jpg")));
        background.setFillParent(true);
        initGUI();
    }

    private void initGUI(){
        Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        final Label title = new Label("Make your profile", skin);
        final TextButton makeProfile = new TextButton("Make profile", skin, "default");
        final TextArea textArea = new TextArea(game.player, skin);

        title.setFontScale(5);
        title.setAlignment(5);
        makeProfile.getLabel().setFontScale(2);
        makeProfile.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (config.getSound().equals("on")) {
                    click.play();
                }
                game.player = textArea.getText().trim().replace('\n', '.').replace('\n', '.');
                dispose();
            }
        });

        stage.addActor(title);
        stage.addActor(makeProfile);
        stage.addActor(textArea);

        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.add(title).padBottom(20f).width(200).height(100);
        table.row();
        table.add(textArea).padBottom(20f).width(400).height(100);
        table.row();
        table.add(makeProfile).padBottom(20f).width(250).height(100);
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

        game.batch.end();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {

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
