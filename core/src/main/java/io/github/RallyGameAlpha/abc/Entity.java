package io.github.RallyGameAlpha.abc;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public abstract class Entity {
    Texture texture;
    Sprite sprite;
    FitViewport viewport;
    SpriteBatch batch;


    public void draw(SpriteBatch batch) {
    }

    ;

    public void update(float delta) {
    }

    ;

    public void dispose() {
    }

    ;

}
