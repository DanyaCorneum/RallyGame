package io.github.RallyGameAlpha.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.RallyGameAlpha.RallyGame;
import io.github.RallyGameAlpha.abc.Entity;

public class Finish implements Entity {
    public Texture texture;
    public Sprite sprite;
    public FitViewport viewport;
    public SpriteBatch batch;
    public float speed;
    public float trueSpeed;
    public float boostSpeed;
    public float brakeSpeed;

    public Finish(RallyGame game){
        texture = new Texture("finish.png");
        sprite = new Sprite(texture);
        viewport = game.viewport;
        batch = game.batch;
        sprite.setSize(20, 10);
        this.speed = 0.1f;
        this.trueSpeed = this.speed;
        this.boostSpeed = 0.15f;
        this.brakeSpeed = 0.05f;
    }
    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
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
