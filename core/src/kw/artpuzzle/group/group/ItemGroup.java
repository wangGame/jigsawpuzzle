package kw.artpuzzle.group.group;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.resource.cocosload.CocosResource;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/4 22:15
 */
public class ItemGroup extends Group {
    public ItemGroup(){
        this(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public ItemGroup(Runnable runnable){
        Group group = CocosResource.loadFile("cocos/levelitemview.json");
        setSize(group.getWidth(),group.getHeight());
        addActor(group);
        group.setPosition(getWidth()/2.0f,getHeight()/2.0f, Align.center);
        setOrigin(Align.center);
        addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                runnable.run();
            }
        });
    }
}
