package io.github.RallyGameAlpha.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.RallyGameAlpha.RallyGame;
import io.github.RallyGameAlpha.abc.Entity;

public class Background implements Entity {
    Texture texture;
    Sprite sprite;
    FitViewport viewport;
    SpriteBatch batch;

    public Background(RallyGame game) {
        this.viewport = game.viewport;
        this.batch = game.batch;

    }

    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void dispose() {

    }
}
