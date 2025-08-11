package io.github.RallyGameAlpha.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import io.github.RallyGameAlpha.RallyGame;

public class ScoreWriter {
    static public void write(int score, RallyGame game) {
        FileHandle file = Gdx.files.local("data/tableOfRecords.txt");
        file.writeString(game.player + " " + score + "\n", true);

        ScoreReader.read("data/tableOfRecords.txt");
    }
}
