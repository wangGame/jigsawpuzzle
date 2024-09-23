package kw.artpuzzle.group.group;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.kw.gdx.asset.Asset;

public class CutGroup extends Group {
    private float baseScale = 1.f;
    public CutGroup(){
        setSize(500,500);
        setDebug(true);
        Image png = new Image(Asset.getAsset().getTexture("234.jpg"));
        addActor(png);
        png.addListener(new ActorGestureListener(){
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                baseScale = png.getScaleX();
            }

            @Override
            public void zoom(InputEvent event, float initialDistance, float distance) {
                super.zoom(event, initialDistance, distance);
                float v = distance / initialDistance;
                png.setScale(v * baseScale);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                png.setOrigin(x,y);
            }
        });
    }
}
