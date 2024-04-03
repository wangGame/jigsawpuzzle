package com.tony.puzzle;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglFiles;
import com.tony.puzzle.desktopnet.DeskDownload;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import kw.artpuzzle.JigSawPuzzle;
import kw.artpuzzle.listener.GameListener;

public class DesktopLauncher {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.y = 0;
        config.height = (int) (640*1.3f);
        config.width = (int) (360 * 1.3f);
        new LwjglApplication(new JigSawPuzzle(
                new DeskDownload(),
                new GameListener() {
                    @Override
                    public void changeLocalPath() {
                        Field field = null;
                        try {
                            field = LwjglFiles.class.getField("localPath");
                            Field modifiersField = Field.class.getDeclaredField("modifiers");
                            modifiersField.setAccessible(true);
                            modifiersField.set(field, field.getModifiers() & ~Modifier.FINAL);
                            field.setAccessible(true);
                            field.set(null, "D://jigsaw/");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void toastShow() {
                        System.out.println("no More Coin");
                    }
                }),config);
    }
}