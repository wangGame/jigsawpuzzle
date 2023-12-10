package kw.artpuzzle.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;

import kw.artpuzzle.constant.GameConstant;

public class TempView extends Group {
    private Image preImage;
    public TempView(Texture texture){
        preImage = new Image(texture);
        Group group = new Group();
        addActor(group);
        Texture textureTemp = Asset.getAsset().getTexture("out1/" + 0 + ".png");
        float startX = -textureTemp.getWidth() / 4.0f;
        float startY = -textureTemp.getHeight() / 4.0f;
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
                startX = -textureTemp.getWidth()/4.0f;
                startY += image.getHeight()-300;
            }
        }
        group.setSize(1800,1800);
        preImage.setSize(1800,1800);
        group.setOrigin(Align.center);
        group.setVisible(false);
        setSize(group.getWidth(),group.getHeight());
        preImage.setPosition(getWidth()/2.0f,getHeight()/2.0f,Align.center);
        addActor(preImage);
        preImage.setVisible(false);
        group.setPosition(getWidth()/2.0f,getHeight()/2.0f,Align.center);
        Image playImg = new Image(Asset.getAsset().getTexture("white.png"));
        addActor(playImg);
        playImg.setSize(getWidth(),getHeight());
        playImg.setPosition(getWidth()/2.0f,getHeight()/2.0f,Align.center);
        playImg.setColor(Color.valueOf("AAAAAA"));
        playImg.getColor().a = 0.3f;
    }

    @Override
    public void setScale(float scaleXY) {
        super.setScale(scaleXY);
        GameConstant.modelScale = scaleXY;
    }

    public void setShowPre(boolean isShowPre) {
        preImage.setVisible(isShowPre);
    }

}
