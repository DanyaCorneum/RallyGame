package io.github.RallyGameAlpha.abc;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;

public interface Entity {
    void draw(SpriteBatch batch);

    void update(float delta);

    void dispose();

}
