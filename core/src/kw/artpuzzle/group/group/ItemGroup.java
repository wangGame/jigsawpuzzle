package kw.artpuzzle.group.group;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.resource.cocosload.CocosResource;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/4 22:15
 */
public class ItemGroup extends Group {
    public ItemGroup(){
        Group group = CocosResource.loadFile("cocos/levelitemview.json");
        setSize(group.getWidth(),group.getHeight());
        addActor(group);
        group.setPosition(getWidth()/2.0f,getHeight()/2.0f, Align.center);
    }
}
