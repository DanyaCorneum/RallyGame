package io.github.RallyGameAlpha.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.RallyGameAlpha.RallyGame;
import io.github.RallyGameAlpha.abc.Entity;

public class Background implements Entity {
    Array<Sprite> layers;
    Sprite currentBg;
    public Sprite winTitle;
    FitViewport viewport;
    SpriteBatch batch;
    float timer;

    public Background(RallyGame game) {
        this.viewport = game.viewport;
        this.batch = game.batch;
        this.winTitle = new Sprite(new Texture("background/win.png"));
        this.winTitle.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
        layers = new Array<>();
        layers.add(new Sprite(new Texture("background/background1.png")));
        layers.add(new Sprite(new Texture("background/background2.png")));
        layers.add(new Sprite(new Texture("background/background3.png")));

        for (Sprite layer : layers) {
            layer.setSize(viewport.getWorldWidth(), viewport.getWorldHeight());
        }


    }

    @Override
    public void draw(SpriteBatch batch) {
        currentBg.draw(batch);

    }

    @Override
    public void update(float delta) {
        timer += delta+.1f;
        currentBg = layers.get((int)timer % 3);

    }


    @Override
    public void dispose() {

    }
}
