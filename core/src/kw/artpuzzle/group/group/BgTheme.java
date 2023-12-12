package kw.artpuzzle.group.group;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.scrollpane.ScrollPane;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/9 22:48
 */
public class BgTheme extends Group {
    private ScrollPane pane;
    Table themeTable;
    public BgTheme(){
        setDebug(true);
        setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT);
        Image bg = new Image(new NinePatch(
                Asset.getAsset().getTexture("white.png"),
                4,4,4,4));
        bg.setSize(getWidth(),getHeight());
        addActor(bg);
        Label themeTitle = new Label("",new Label.LabelStyle(){
            {
                font = Asset.getAsset().loadBitFont("cocos/font/inter-semi-32.fnt");
            }
        });
        addActor(themeTitle);
        themeTitle.setColor(Color.BLACK);
        themeTitle.setText("change theme");
        themeTitle.pack();
        themeTitle.setPosition(getWidth()/2.0f,getHeight() - 70,Align.center);
        Label themefinish = new Label("",new Label.LabelStyle(){
            {
                font = Asset.getAsset().loadBitFont("cocos/font/inter-semi-32.fnt");
            }
        });
        addActor(themefinish);
        themefinish.setText("finish");
        themefinish.pack();
        themefinish.setPosition(getWidth() - 30,getHeight() - 70,Align.right);
        themefinish.setColor(Color.RED);
        pane = new ScrollPane(themeTable = new Table(){
            {
                FileHandle internal = Gdx.files.internal("themebg");
                int index = 0;
                for (FileHandle fileHandle : internal.list()) {
                    index ++;
                    ThemeItem themeItem = new ThemeItem(fileHandle.name(),new Runnable(){
                        @Override
                        public void run() {
                            for (Actor child : themeTable.getChildren()) {
                                ((ThemeItem)(child)).hideIconSelect();
                            }
                        }
                    });
                    add(themeItem);
                    if (index % 3 == 0){
                        row();
                    }
                }
                pack();
                align(Align.top);
            }
        });
        pane.setSize(getWidth(),getHeight() - 140);
        addActor(pane);
    }
}
