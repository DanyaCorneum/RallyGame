package io.github.RallyGameAlpha.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class AppConfig {
    private final FileHandle config;
    private String fullScreen;
    private String sound;
    private String music;
    private String difficulty;
    private final String[] settings;

    public AppConfig(){
        this.config = Gdx.files.local("data/config");
        this.settings = config.readString().split("\n");
        this.fullScreen = this.settings[0];
        this.sound = this.settings[1];
        this.music = this.settings[2];
        this.difficulty= this.settings[3];
    }

    private void updateConfig(){
        this.config.writeString("", false);
        for(String set : settings){
            this.config.writeString(set + "\n", true);
        }
    }

    public void setFullScreen(String fullScreen) {
        this.fullScreen = fullScreen;
        this.settings[0] = this.fullScreen;
        this.updateConfig();
    }

    public String getFullScreen(){
        return this.fullScreen;
    }

    public void setSound(String sound){
        this.sound = sound;
        this.settings[1] = this.sound;
        this.updateConfig();
    }

    public String getSound(){
        return this.sound;
    }


    public void setMusic(String music) {
        this.music = music;
        this.settings[2] = this.music;
        this.updateConfig();
    }

    public String getMusic(){
        return this.music;
    }

    public void setDifficulty(String difficulty){
        this.difficulty = difficulty;
        this.settings[3] = this.difficulty;
        this.updateConfig();
    }

    public String getDifficulty(){
        return this.difficulty;
    }


}
