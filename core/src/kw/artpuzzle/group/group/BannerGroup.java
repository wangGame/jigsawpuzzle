package kw.artpuzzle.group.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.kw.gdx.resource.cocosload.CocosResource;

import kw.artpuzzle.data.CollectionBean;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/12 11:23
 */
public class BannerGroup extends Group {
    public BannerGroup(CollectionBean collectionBean){
        Group group = CocosResource.loadFile("cocos/bannergroup.json");
        addActor(group);
        setSize(group.getWidth(),group.getHeight());
        Label collectiontitle = group.findActor("collectiontitle");
        collectiontitle.setText(collectionBean.getDesc());
    }
}
