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
    Texture texture1;
    Texture texture2;
    Texture rightTexture;
    Texture leftTexture;
    Sprite sprite;
    public Rectangle hitBox;
    float speed;
    float trueSpeed;
    float boostSpeed;
    float brakeSpeed;
    boolean init;
    boolean isHit;
    public int score;
    float timer;


    public Player(FitViewport viewport) {
        this.viewport = viewport;
        this.texture = new Texture(Gdx.files.internal("player/playerStand.png"));
        this.texture1 = new Texture(Gdx.files.internal("player/playerStand.png"));
        this.texture2 = new Texture("player/playerStand2.png");
        this.rightTexture = new Texture(Gdx.files.internal("player/playerRight.png"));
        this.leftTexture = new Texture(Gdx.files.internal("player/playerLeft.png"));
        this.sprite = new Sprite(texture);
        this.sprite.setSize(400, 200);
        this.hitBox = new Rectangle();
        this.speed = 10f;
        this.trueSpeed = 10f;
        this.boostSpeed = 15;
        this.brakeSpeed = 5f;
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
        texture1 = new Texture("player/playerDamage.png");
        texture2 = new Texture("player/playerStand2Damage.png");
        leftTexture = new Texture("player/playerLeftDamage.png");
        rightTexture = new Texture("player/playerRightDamage.png");

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                texture1 = new Texture("player/playerStand.png");
                texture2 = new Texture("player/playerStand2.png");
                leftTexture = new Texture("player/playerLeft.png");
                rightTexture = new Texture("player/playerRight.png");
                isHit = false;

            }
        }, .5f);
        score -= 10;
        Gdx.app.log("Player", String.valueOf(score));
    }

    public void collect() {
        if (isHit) return;

        isHit = true;
        texture1 = new Texture("player/playerScore.png");
        texture2 = new Texture("player/playerStand2Score.png");
        leftTexture = new Texture("player/playerLeftScore.png");
        rightTexture = new Texture("player/playerRightScore.png");

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                texture1 = new Texture("player/playerStand.png");
                texture2 = new Texture("player/playerStand2.png");
                leftTexture = new Texture("player/playerLeft.png");
                rightTexture = new Texture("player/playerRight.png");
                isHit = false;
            }
        }, .5f);
        score += 30;
        Gdx.app.log("Player", String.valueOf(score));
    }


    @Override
    public void update(float delta) {
        timer += delta+.1f;
        if ((int)timer % 2 == 0){
            texture = texture1;
        } else {
            texture = texture2;
        }


        hitBox.set(this.sprite.getX() * 1.2f, this.sprite.getY(), this.sprite.getWidth() * 0.8f, this.sprite.getHeight() / 2);
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        sprite.setX(MathUtils.clamp(sprite.getX(), 0.05f, worldWidth - sprite.getWidth() * 1.5f));
        sprite.setY(MathUtils.clamp(sprite.getY(), 0.05f, worldHeight - sprite.getHeight() * 2f));
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            sprite.setTexture(rightTexture);
            sprite.translateX(speed * (float) Math.cos(Math.PI / 6));
            sprite.translateY(-speed * (float) Math.cos(Math.PI / 2.7));
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            sprite.setTexture(leftTexture);
            sprite.translateX(-speed * (float) Math.cos(Math.PI / 6));
            sprite.translateY(speed * (float) Math.cos(Math.PI / 2.7));
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
