package io.github.RallyGameAlpha.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.RallyGameAlpha.abc.Entity;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;
import java.sql.Time;

public class Player implements Entity {
    FitViewport viewport;
    Texture texture;
    Sprite sprite;
    public Rectangle hitBox;
    float speed;
    float trueSpeed;
    float boostSpeed;
    float brakeSpeed;
    boolean init;
    boolean isHit;
    public int score;


    public Player(FitViewport viewport) {
        this.viewport = viewport;
        this.texture = new Texture(Gdx.files.internal("player/playerStand.png"));
        this.sprite = new Sprite(texture);
        this.sprite.setSize(2.5f, 1.5f);
        this.hitBox = new Rectangle();
        this.speed = .05f;
        this.trueSpeed = .05f;
        this.boostSpeed = .1f;
        this.brakeSpeed = .02f;
        init = false;
        isHit = false;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (!init) {
            sprite.setPosition(0.05f, viewport.getWorldHeight() - sprite.getHeight() * 2f);
            init = true;
        }
        sprite.draw(batch);
    }

    public void drawHit() {
        if (isHit) return;

        isHit = true;
        texture = new Texture("player/playerDamage.png");

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                texture = new Texture("player/playerStand.png");
                isHit = false;

            }
        }, 1.5f);
        score -= 10;
        Gdx.app.log("Player", String.valueOf(score));
    }

    public void collect() {
        if (isHit) return;

        isHit = true;
        texture = new Texture("player/playerScore.png");

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                texture = new Texture("player/playerStand.png");
                isHit = false;
            }
        }, .5f);
        score += 30;
        Gdx.app.log("Player", String.valueOf(score));
    }


    @Override
    public void update(float delta) {

        hitBox.set(this.sprite.getX() * 1.2f, this.sprite.getY(), this.sprite.getWidth() * 0.8f, this.sprite.getHeight() / 2);
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        sprite.setX(MathUtils.clamp(sprite.getX(), 0.05f, worldWidth - sprite.getWidth() * 1.5f));
        sprite.setY(MathUtils.clamp(sprite.getY(), 0.05f, worldHeight - sprite.getHeight() * 2f));
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            sprite.setTexture(new Texture(Gdx.files.internal("player/playerRight.png")));
            sprite.translateX(speed * (float) Math.cos(Math.PI / 4.20));
            sprite.translateY(-speed * (float) Math.cos(Math.PI / 2.90));
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            sprite.setTexture(new Texture(Gdx.files.internal("player/playerLeft.png")));
            sprite.translateX(-speed * (float) Math.cos(Math.PI / 4.20));
            sprite.translateY(speed * (float) Math.cos(Math.PI / 2.90));
        } else {
            sprite.setTexture(texture);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            speed = boostSpeed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            speed = brakeSpeed;
        } else {
            speed = trueSpeed;
        }

    }

    @Override
    public void dispose() {

    }
}
