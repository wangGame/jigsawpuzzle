package kw.artpuzzle.group.group;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.scrollpane.ScrollPane;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/5 15:01
 */
public abstract class MyPzBaseGroup extends Group {
    public static MyPzBaseGroup instance;
    private ScrollPane levelListScrollPanel;
    private Table contentTable;
    public MyPzBaseGroup(){
        levelListScrollPanel = new ScrollPane(contentTable = new Table(){{

        }});
        addActor(levelListScrollPanel);
        float height = Constant.GAMEHIGHT - 142 - 142 - 100;
        setSize(Constant.GAMEWIDTH,height);
        levelListScrollPanel.setSize(getWidth()/2.0f,getHeight()/2.0f);
        levelListScrollPanel.setPosition(getWidth()/2.0f,getHeight()/2.0f, Align.center);
    }

    public Table getContentTable() {
        return contentTable;
    }

    public static MyPzBaseGroup getInstance() {
        if (instance!=null)return instance;
        return instance = new MyPuzzleGroup();
    }
}
