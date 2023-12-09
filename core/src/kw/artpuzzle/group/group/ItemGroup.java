package kw.artpuzzle.group.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.resource.cocosload.CocosResource;

import kw.artpuzzle.constant.LevelConfig;
import kw.artpuzzle.data.LevelBean;
import kw.artpuzzle.group.ItemImage;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/4 22:15
 */
public class ItemGroup extends Group {
    private ItemImage itemGroup;
    public ItemGroup(){
        Group group = CocosResource.loadFile("cocos/levelitemview.json");
        setSize(group.getWidth(),group.getHeight());
        addActor(group);
        group.setPosition(getWidth()/2.0f,getHeight()/2.0f, Align.center);
        setOrigin(Align.center);
        addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });
    }

    public ItemGroup(LevelBean levelBean,Runnable runnable){
        Group group = CocosResource.loadFile("cocos/levelitemview.json");
        setSize(group.getWidth(),group.getHeight());
        addActor(group);
        group.setPosition(getWidth()/2.0f,getHeight()/2.0f, Align.center);
        setOrigin(Align.center);
        itemGroup = new ItemImage(levelBean);
        group.addActor(itemGroup);
        itemGroup.setPosition(group.getWidth()/2.0f,getHeight()/2.0f,Align.center);
        addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                LevelConfig.levelIndex = levelBean.getSortId();
                runnable.run();
            }
        });
        group.findActor("lock").setVisible(false);
        Group process = group.findActor("process");
        Label processlabel = process.findActor("processlabel");
        processlabel.setColor(Color.valueOf("#AAAAAA"));
    }

}
