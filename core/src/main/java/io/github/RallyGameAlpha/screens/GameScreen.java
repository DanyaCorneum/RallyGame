package io.github.RallyGameAlpha.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.RallyGameAlpha.RallyGame;
import io.github.RallyGameAlpha.entities.Background;
import io.github.RallyGameAlpha.entities.ObjectGame;
import io.github.RallyGameAlpha.entities.Player;
import io.github.RallyGameAlpha.utils.AppConfig;


public class GameScreen implements Screen {
    final RallyGame game;

    AppConfig config;
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
    Music music;
    float timeToStart;
    boolean pause;

    public GameScreen(RallyGame game) {
        this.game = game;
        this.config = new AppConfig();

        this.player = new Player(game.viewport);
        this.objects = new Array<>();
        this.background = new Background(game);
        delta = Gdx.graphics.getDeltaTime();
        if (config.getDifficulty().equals("normal")) {
            gameTimer = 100f;
            length = 100f;
        } else {
            gameTimer = 100f;
            length = 200f;
        }
        time = game.font;
        time.setColor(Color.WHITE);
        gameGo = true;
        this.music = Gdx.audio.newMusic(Gdx.files.internal("sounds/rave.mp3"));
        if (config.getMusic().equals("on")) {
            music.setLooping(true);
            music.setVolume(.5f);
            music.play();
        }
        timeToStart = 5;
        pause = false;
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
        if (timeToStart < 0) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                music.pause();
                pause = !pause;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && pause) {
                if (config.getMusic().equals("on")) {
                    game.music.play();
                }
                game.setScreen(new MainMenuScreen(game));
            }
            if (length > 5) {
                player.update(delta);
            }
        }
    }

    public void logic() {
        if (!pause) {
            if (config.getMusic().equals("on")) {
                music.play();
            }
            if (timeToStart < 0) {
                if (gameGo) {
                    gameTimer -= delta;
                }
                length -= delta;
                if (!player.isHit) {
                    if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                        length -= delta * 2f;
                    } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                        length -= delta * 0.5f;
                    }
                }
                if (gameTimer > 0 && length > 5) {
                    background.update(delta);
                    for (int i = objects.size - 1; i >= 0; i--) {
                        ObjectGame object = objects.get(i);
                        float width = object.sprite.getWidth();
                        float height = object.sprite.getHeight();
                        object.sprite.translateY(-object.speed * (float) Math.cos(Math.PI / 8.20));
                        object.sprite.translateX(-object.speed * (float) Math.cos(Math.PI / 8.20));
                        object.hitBox.set(object.sprite.getX(), object.sprite.getY(), width, height);
                        if (!player.isHit) {
                            object.update(delta);
                        }
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
                    if (config.getDifficulty().equals("hard")) {
                        timerObjects += delta;
                        if (timerObjects > 1f) {
                            timerObjects = 0;
                            createObject();
                        }
                    } else {
                        timerObjects += delta*0.5f;
                        if (timerObjects > 1f) {
                            timerObjects = 0;
                            createObject();
                        }
                    }
                } else if (length < 5 && length > 0) {
                    objects.clear();
                    length -= delta * 0.01f;
                    if (music.getVolume() - delta > 0) {
                        music.setVolume(music.getVolume() - delta);
                    }

                } else {
                    gameGo = false;
                    if (config.getMusic().equals("on")) {
                        game.music.play();
                    }
                    if (config.getDifficulty().equals("hard")) {
                        game.setScreen(new TableRecordsScreen(this.game, (player.score + 100) * 2));

                    } else {
                        game.setScreen(new TableRecordsScreen(this.game, (player.score + 100)));

                    }
                    music.stop();
                    dispose();
                }
            } else {
                timeToStart -= delta;
            }
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
        if (!pause) {

            player.draw(game.batch);
            for (ObjectGame r : objects) {
                r.draw(game.batch);
            }
        } else {
            game.font.draw(game.batch, "Press Escape to exit from pause", 0.1f, 50);
            game.font.draw(game.batch, "Press Enter to exit from game", 0.1f, 100);
        }
        if (length < 5) {
            game.font.setColor(Color.RED);
            game.font.draw(game.batch, "FINISH", game.viewport.getWorldWidth() / 2 - 50, game.viewport.getWorldHeight(
            ) - 20);
        } else {
            time.draw(game.batch, "Time: " + (int) gameTimer, textX, textY);
            time.draw(game.batch, "To finish: " + ((length - 5) > 0 ? (int) (length - 5) : 0), textX, textY - 50f);
        }
        if (timeToStart > 0) {
            game.font.draw(game.batch, String.valueOf((int) timeToStart), game.viewport.getWorldWidth() / 2 - game
                .font.getScaleX(), game.viewport.getWorldHeight(
            ) / 2 - game.font.getScaleY());
            game.font.draw(game.batch, "Press LeftShift for boost", 0.1f, 50);
            game.font.draw(game.batch, "Press Space for brake", game.viewport.getWorldWidth() - 300, 50);
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
        player.dispose();
        background.dispose();
    }
}
