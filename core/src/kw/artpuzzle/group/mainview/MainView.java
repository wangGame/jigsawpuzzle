package kw.artpuzzle.group.mainview;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.scrollpane.ScrollPane;

import kw.artpuzzle.group.group.ItemGroup;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/4 19:54
 */
public class MainView extends BaseView {
    private ScrollPane levelScrollPanel;
    public MainView(Runnable runnable){
        levelScrollPanel = new ScrollPane(new Table(){{
            for (int i = 0; i < 10; i++) {
                add(new ItemGroup()).pad(15);
                add(new ItemGroup()).pad(15);
                row();
            }
            pack();
            align(Align.top);
        }});
        levelScrollPanel.setSize(getWidth(),getHeight());
        levelScrollPanel.setPosition(getWidth()/2.0f,getHeight()/2.0f,Align.center);
        addActor(levelScrollPanel);
//        addListener(new OrdinaryButtonListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                super.clicked(event, x, y);
//                //open
//                runnable.run();
//            }
//        });
    }
}
