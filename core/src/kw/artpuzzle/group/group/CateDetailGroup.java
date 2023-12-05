package kw.artpuzzle.group.group;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/5 22:31
 */
public class CateDetailGroup extends Group {
    public CateDetailGroup(){
        setDebug(true);
        setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT - 140);
        Image bg = new Image(new NinePatch(
                Asset.getAsset().getTexture("white.png"),
                2,2,2,2));
        addActor(bg);
        bg.setSize(getWidth(),getHeight());
    }
}
