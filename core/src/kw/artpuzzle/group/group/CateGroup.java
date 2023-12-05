package kw.artpuzzle.group.group;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/5 11:39
 */
public class CateGroup extends Group {
    public CateGroup(String s){
        setSize(Constant.GAMEWIDTH-20,115);
        Image icon = new Image(Asset.getAsset().getTexture("common/categorybg.png"));
        addActor(icon);
        icon.setPosition(60,getHeight()/2.0f, Align.left);
        Label cateLabel = new Label("",new Label.LabelStyle(){{
            font = Asset.getAsset().loadBitFont("cocos/font/inter-semi-32.fnt");
        }});
        cateLabel.setText(s);
        addActor(cateLabel);
        cateLabel.setX(icon.getX(Align.right)+20);
        cateLabel.setY(icon.getY(Align.center));
    }
}
