package io.github.RallyGameAlpha.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class ScoreWriter {
    static public void write(int score) {
        FileHandle dir = Gdx.files.local("data/");
        if (!dir.exists()) {
            dir.mkdirs(); // создаёт папку, если её нет
        }

        FileHandle file = Gdx.files.local("data/tableOfRecords.txt");
        file.writeString(score + "\n", true);

        ScoreReader.read("data/tableOfRecords.txt");
    }
}
