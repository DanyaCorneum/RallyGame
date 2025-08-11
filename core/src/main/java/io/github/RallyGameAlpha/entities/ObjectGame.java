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
        this.viewport = game.viewport;
        if (MathUtils.random(1, 10) - 4 > 0) {

            this.texture = new Texture(Gdx.files.internal("entities/rock.png"));
            this.sprite = new Sprite(texture);
            float size = MathUtils.random(150, 250);
            this.sprite.setSize(size, size);
            id = 0;
        } else {
            this.texture = new Texture(Gdx.files.internal("entities/star.png"));
            this.sprite = new Sprite(texture);
            this.sprite.setSize(100, 100);
            id = 1;
        }

        this.hitBox = new Rectangle();
        this.sprite.setSize(sprite.getWidth(), sprite.getHeight());
        this.speed = 5f;
        this.trueSpeed = this.speed;
        this.boostSpeed = 15f;
        this.brakeSpeed = 2f;
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
