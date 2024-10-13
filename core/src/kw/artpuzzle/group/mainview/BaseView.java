package kw.artpuzzle.group.mainview;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/4 21:39
 */
public abstract class BaseView extends Group {
    protected int levelIndex;
    protected int initNumber = 10;
    public BaseView(){
        setSize(Constant.GAMEWIDTH, Constant.GAMEHIGHT - 142 - 142);
    }

    public abstract void addLevelItem();

    public abstract void update();
}
