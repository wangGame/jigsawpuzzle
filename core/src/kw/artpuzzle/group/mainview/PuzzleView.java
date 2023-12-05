package kw.artpuzzle.group.mainview;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;

import kw.artpuzzle.group.group.MyPuzzleGroup;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/4 21:30
 */
public class PuzzleView extends BaseView {
    public Group contentGroup;
    public PuzzleView(){
        Group contentTitleGroup = new Group();
        addActor(contentTitleGroup);
        contentTitleGroup.setSize(990,92);
        Image contentTitleBg = new Image(Asset.getAsset().getTexture("common/btnbg.png"));
        contentTitleGroup.addActor(contentTitleBg);
        contentTitleBg.setPosition(contentTitleGroup.getWidth()/2.0f,contentTitleGroup.getHeight()/2.0f,Align.center);
        contentTitleGroup.setDebug(true);
        contentTitleGroup.setY(getHeight(),Align.top);
        String str[] = {
                "my puzzle",
                "my piclist",
                "playing"
        };
        for (int i = 0; i < 3; i++) {
            Group group = new Group();
            group.setSize(330.0f,46.0f);
            Label labelTitle = new Label("",new Label.LabelStyle(){{
                font = Asset.getAsset().loadBitFont("cocos/font/inter-semi-32.fnt");
            }});
            labelTitle.setText(str[i]);
            labelTitle.pack();
            labelTitle.setColor(Color.BLACK);
            labelTitle.setPosition(165.0f+i*330.0f,46.0f,Align.center);
            contentTitleGroup.addActor(labelTitle);
            contentTitleGroup.setX(getWidth()/2.0f,Align.center);
        }
        contentGroup = new Group();
        contentGroup.setSize(getWidth(),getHeight() - 100);
        addActor(contentGroup);
        contentGroup.setY(0);
        defaultView();
    }

    private void defaultView() {
        contentGroup.addActor(MyPuzzleGroup.getInstance());
    }
}