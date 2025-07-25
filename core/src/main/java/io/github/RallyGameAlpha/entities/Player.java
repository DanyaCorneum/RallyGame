package io.github.RallyGameAlpha.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.RallyGameAlpha.abc.Entity;

import java.awt.*;

public class Player extends Entity {
    FitViewport viewport;
    Texture texture;
    Sprite sprite;
    Rectangle hitBox;
    float speed;


    public Player(FitViewport viewport) {
        this.viewport = viewport;
        this.texture = new Texture(Gdx.files.internal("player.png"));
        this.sprite = new Sprite(texture);
        this.sprite.setSize(1, 1);
        this.hitBox = new Rectangle();
        this.speed = 20.0f;
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
        Gdx.app.log("Player", "draw");
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void dispose(){

    }
}
