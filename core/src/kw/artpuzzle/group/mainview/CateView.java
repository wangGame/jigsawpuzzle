package kw.artpuzzle.group.mainview;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.scrollpane.ScrollPane;

import kw.artpuzzle.group.group.CateGroup;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/4 21:30
 */
public class CateView extends BaseView {
    private ScrollPane cateScrollpane;
    public CateView(Runnable runnable){
        String str[] = {
                "ABCDEF",
                "ABCDEF",
                "ABCDEF"
        };
        cateScrollpane = new ScrollPane(new Table(){{
            for (String s : str) {
                CateGroup cateGroup = new CateGroup(s);
                add(cateGroup).pad(10);
                cateGroup.addListener(new OrdinaryButtonListener(1.0f){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        runnable.run();
                    }
                });
                row();
            }
            pack();
            align(Align.top);
        }});
        cateScrollpane.setSize(getWidth(),getHeight());
        addActor(cateScrollpane);
    }
}