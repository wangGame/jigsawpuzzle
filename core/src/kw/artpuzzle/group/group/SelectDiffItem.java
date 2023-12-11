package kw.artpuzzle.group.group;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.ArrayMap;
import com.kw.gdx.resource.cocosload.CocosResource;

import kw.artpuzzle.data.GameData;
import kw.artpuzzle.data.SelectItemBean;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/11 19:35
 */
public class SelectDiffItem extends Group {
    public SelectDiffItem(SelectItemBean selectItemBean){
        Group group = CocosResource.loadFile("cocos/selectitem.json");
        addActor(group);
        setSize(group.getWidth(),group.getHeight());
    }
}
