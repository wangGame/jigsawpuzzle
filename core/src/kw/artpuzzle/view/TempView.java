package kw.artpuzzle.view;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.kw.gdx.asset.Asset;

public class TempView extends Group {
    public TempView(){
        Group group = new Group();
        addActor(group);
        float startX = 0;
        float startY = 0;
        int index = 0;
        for (int i = 0; i < 36; i++) {
            Image image = new Image(Asset.getAsset().getTexture("out1/"+i+".png"));

            image.setName(i+"");
            group.addActor(image);
            image.setPosition(startX, startY);

            startX += image.getWidth()-300;
            index++;
            if (index == 6) {
                index = 0;
                startX = 0;
                startY += image.getHeight()-300;
            }
        }
        group.setScale(0.4f);
        group.setSize(1800,1800);
        group.setDebug(true);
//        setScale(0.3f);
    }
}
