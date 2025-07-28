package io.github.RallyGameAlpha.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.RallyGameAlpha.RallyGame;
import io.github.RallyGameAlpha.abc.Entity;

public class ObjectGame implements Entity {
    FitViewport viewport;
    public Texture texture;
    public Sprite sprite;
    public Rectangle hitBox;
    public float speed;
    float trueSpeed;
    float boostSpeed;
    float brakeSpeed;
    public int id;

    public ObjectGame(RallyGame game) {
        Gdx.app.log("rock", "okay");
        this.viewport = game.viewport;
        if (MathUtils.random(1, 5) == 5) {

            this.texture = new Texture(Gdx.files.internal("entities/rock.png"));
            id = 0;
        } else {
            this.texture = new Texture(Gdx.files.internal("entities/star.png"));
            id = 1;
        }
        this.sprite = new Sprite(texture);
        this.hitBox = new Rectangle();
        this.sprite.setSize(1, 1);
        this.speed = 0.1f;
        this.trueSpeed = this.speed;
        this.boostSpeed = 0.15f;
        this.brakeSpeed = 0.05f;
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
        hitBox.set(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void update(float delta) {
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
