package kw.artpuzzle.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;

import kw.artpuzzle.constant.Constant;

public class TempView extends Group {
    public TempView(){
        setDebug(true);
        Group group = new Group();
        addActor(group);
        float startX = -100;
        float startY = -120;
        Texture texture = Asset.getAsset().getTexture("out1/" + 0 + ".png");
        startX = -texture.getWidth()/4.0f;
        startY = -texture.getHeight() / 4.0f;
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
                startX = -texture.getWidth()/4.0f;
                startY += image.getHeight()-300;
            }
        }
        group.setSize(1800,1800);
        group.setOrigin(Align.center);
        group.setDebug(true);
        setSize(group.getWidth(),group.getHeight());
        group.setPosition(getWidth()/2.0f,getHeight()/2.0f,Align.center);
    }

    @Override
    public void setScale(float scaleXY) {
        super.setScale(scaleXY);
        Constant.modelScale = scaleXY;
    }
}
