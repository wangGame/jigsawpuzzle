package com.tony.puzzle;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import kw.artpuzzle.JigSawPuzzle;

public class DesktopLauncher {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.y = 0;
        config.height = (int) (640*1.3f);
        config.width = (int) (360*1.3f);
        new LwjglApplication(new JigSawPuzzle(),config);
    }
}