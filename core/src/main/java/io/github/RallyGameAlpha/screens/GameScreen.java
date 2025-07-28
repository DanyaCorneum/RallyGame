package io.github.RallyGameAlpha.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import io.github.RallyGameAlpha.RallyGame;
import io.github.RallyGameAlpha.abc.Entity;
import io.github.RallyGameAlpha.entities.ObjectGame;
import io.github.RallyGameAlpha.entities.Player;


import java.util.ArrayList;

public class GameScreen implements Screen {
    final RallyGame game;
    Player player;
    Sprite placeholder;
    Array<ObjectGame> objects;
    float delta;
    float timer;

    public GameScreen(RallyGame game) {
        this.game = game;
        this.placeholder = new Sprite(new Texture(Gdx.files.internal("palceholder.png")));
        this.placeholder.setSize(10, 8);
        this.player = new Player(game.viewport);
        this.objects = new Array<>();
        delta = Gdx.graphics.getDeltaTime();
    }

    private void createObject() {
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();

        ObjectGame object = new ObjectGame(this.game);
        object.sprite.setX(MathUtils.random(5f, worldWidth + 2));
        object.sprite.setY(MathUtils.random(7f, worldHeight));
        objects.add(object);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        input();
        logic();
        draw();
    }

    public void input() {
        player.update(delta);
    }

    public void logic() {
        for (int i = objects.size - 1; i >= 0; i--) {
            ObjectGame object = objects.get(i);
            float width = object.sprite.getWidth();
            float height = object.sprite.getHeight();
            object.sprite.translateY(-object.speed * (float) Math.cos(Math.PI / 4.20));
            object.sprite.translateX(-object.speed * (float) Math.cos(Math.PI / 3.20));
            object.hitBox.set(object.sprite.getX(), object.sprite.getY(), width, height);
            object.update(delta);

            if (object.sprite.getY() < -height) {
                objects.removeIndex(i);
            } else if (player.hitBox.overlaps(object.hitBox) && object.id == 0) {
                player.drawHit();
                objects.removeIndex(i);

            } else if (player.hitBox.overlaps(object.hitBox) && object.id == 1){
                player.collect();
                objects.removeIndex(i);
            }
        }


        timer += delta;
        if (timer > 1f) {
            timer = 0;
            createObject();

        }

    }

    public void draw() {
        ScreenUtils.clear(Color.GRAY);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.batch.begin();
        placeholder.draw(game.batch);
        player.draw(game.batch);
        for (ObjectGame r : objects) {
            r.draw(game.batch);
        }
        game.batch.end();
    }

    ;

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
