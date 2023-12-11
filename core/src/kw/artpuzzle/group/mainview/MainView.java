package kw.artpuzzle.group.mainview;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ArrayMap;
import com.kw.gdx.scrollpane.ScrollPane;

import kw.artpuzzle.data.GameData;
import kw.artpuzzle.data.LevelBean;
import kw.artpuzzle.group.group.ItemGroup;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/4 19:54
 */
public class MainView extends BaseView {
    private ScrollPane levelScrollPanel;
    public MainView(Runnable runnable){
        ArrayMap<Integer, LevelBean> levelData = GameData.getInstance().getLevelSortData();
        levelScrollPanel = new ScrollPane(new Table(){{
            int index = 0;
            for (int i = 1; i <= levelData.size; i++) {
                LevelBean levelBean = levelData.get(i);
                if (levelBean.getType().equals("COLLECTION")){
                    add(new ItemGroup(levelBean, runnable)).pad(15);
                    add(new ItemGroup(levelBean, runnable)).pad(15);
                    row();
                }else {
                    index ++;
                    add(new ItemGroup(levelBean, runnable)).pad(15);
                    if (index % 2 == 0) {
                        row();
                    }
                }
            }
            pack();
            align(Align.top);
        }});
        levelScrollPanel.setSize(getWidth(),getHeight());
        levelScrollPanel.setPosition(getWidth()/2.0f,getHeight()/2.0f,Align.center);
        addActor(levelScrollPanel);
    }
}
