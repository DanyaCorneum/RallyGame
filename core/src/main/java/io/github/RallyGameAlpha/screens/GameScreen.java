package io.github.RallyGameAlpha.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.RallyGameAlpha.RallyGame;
import io.github.RallyGameAlpha.entities.Background;
import io.github.RallyGameAlpha.entities.Finish;
import io.github.RallyGameAlpha.entities.ObjectGame;
import io.github.RallyGameAlpha.entities.Player;
import io.github.RallyGameAlpha.utils.ScoreWriter;


public class GameScreen implements Screen {
    final RallyGame game;

    Background background;
    Player player;
    Array<ObjectGame> objects;
    float delta;
    float timerObjects;
    float gameTimer;
    BitmapFont time;
    float gameScore;
    float length;
    boolean gameGo;
    Finish finish;

    public GameScreen(RallyGame game) {
        this.game = game;
        this.player = new Player(game.viewport);
        this.objects = new Array<>();
        this.background = new Background(game);
        delta = Gdx.graphics.getDeltaTime();
        gameTimer = 100f;
        length = 100f;
        time = game.font;
        time.setColor(Color.WHITE);
        gameGo = true;
        finish = new Finish(game);
        finish.sprite.setPosition(game.viewport.getWorldWidth(), game.viewport.getWorldHeight());
    }

    private void createObject() {
        float worldWidth = game.viewport.getWorldWidth();
        float worldHeight = game.viewport.getWorldHeight();

        ObjectGame object = new ObjectGame(this.game);
        object.sprite.setX(MathUtils.random(700f, worldWidth + 2));
        object.sprite.setY(MathUtils.random(700f, worldHeight));
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
        if (gameGo) {
            gameTimer -= delta;
        }
        length -= delta;
        gameScore += 10;
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            length -= delta * 2f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            length -= delta * 0.5f;
        }
        if (gameTimer > 0 && length > 0) {
            background.update(delta);
            if (length <= 7) {
                finish.update(delta);
                finish.sprite.translateY(-finish.speed * (float) Math.cos(Math.PI / 4.0));
                finish.sprite.translateX(-finish.speed * (float) Math.cos(Math.PI / 4.0));
            }
            for (int i = objects.size - 1; i >= 0; i--) {
                ObjectGame object = objects.get(i);
                float width = object.sprite.getWidth();
                float height = object.sprite.getHeight();
                object.sprite.translateY(-object.speed * (float) Math.cos(Math.PI / 8.20));
                object.sprite.translateX(-object.speed * (float) Math.cos(Math.PI / 8.20));
                object.hitBox.set(object.sprite.getX(), object.sprite.getY(), width, height);
                object.update(delta);

                if (object.sprite.getY() < -height) {
                    objects.removeIndex(i);
                } else if (player.hitBox.overlaps(object.hitBox) && object.id == 0) {
                    player.drawHit();
                    objects.removeIndex(i);

                } else if (player.hitBox.overlaps(object.hitBox) && object.id == 1) {
                    player.collect();
                    objects.removeIndex(i);
                }
            }


            timerObjects += delta;
            if (timerObjects > 1f) {
                timerObjects = 0;
                createObject();

            }
        } else {
            gameGo = false;
            game.setScreen(new TableRecordsScreen(this.game, (int) gameScore));
        }

    }

    public void draw() {
        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();
        game.batch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.batch.begin();
        float textX = 0.1f; // 1 единица от левого края мира
        float textY = game.viewport.getWorldHeight() - 0.5f; // 1 единица от верхнего края мира

        background.draw(game.batch);
        if (length <= 7) {
            finish.draw(game.batch);
        }
        player.draw(game.batch);
        for (ObjectGame r : objects) {
            r.draw(game.batch);
        }
        time.draw(game.batch, "Time: " + (int) gameTimer, textX, textY);
        time.draw(game.batch, "To finish: " + (int) length, textX, textY - 50f);
        game.batch.end();

    };

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
        time.dispose();
    }
}
