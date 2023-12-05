package kw.artpuzzle.group.mainview;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kw.gdx.scrollpane.ScrollPane;

import kw.artpuzzle.group.group.CateGroup;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/4 21:30
 */
public class CateView extends BaseView {
    private ScrollPane cateScrollpane;
    public CateView(){
        String str[] = {
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF",
                "ABCDEF"
        };
        cateScrollpane = new ScrollPane(new Table(){{
            for (String s : str) {
                CateGroup cateGroup = new CateGroup(s);
                add(cateGroup).pad(10);
                row();
            }
            pack();
        }});
        cateScrollpane.setSize(getWidth(),getHeight());
        addActor(cateScrollpane);
    }
}