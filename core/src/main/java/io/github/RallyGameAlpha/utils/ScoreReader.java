package io.github.RallyGameAlpha.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class ScoreReader {
    static public String[]  read(String filePath){
        FileHandle file = Gdx.files.internal("data/tableOfRecords.txt");
        String[] s = file.readString().split("\n");
        Gdx.app.log("ScoreReader", Arrays.toString(s));

        return s;
    }
}
