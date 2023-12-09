package kw.artpuzzle.group.mainview;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ArrayMap;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.scrollpane.ScrollPane;

import kw.artpuzzle.data.CateBean;
import kw.artpuzzle.data.GameData;
import kw.artpuzzle.group.group.CateGroup;
import kw.artpuzzle.listener.SignListener;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/4 21:30
 */
public class CateView extends BaseView {
    private ScrollPane cateScrollpane;
    public CateView(SignListener listener){
        ArrayMap<String, CateBean> entries = GameData.getInstance().readCate();
        cateScrollpane = new ScrollPane(new Table(){{
            for (int i = 0; i < entries.size; i++) {
                String keyAt = entries.getKeyAt(i);
                CateBean cateBean = entries.get(keyAt);
                CateGroup cateGroup = new CateGroup(cateBean);
                add(cateGroup).pad(10);
                cateGroup.setSignListener(listener);
                row();
            }
            pack();
            align(Align.top);
        }});
        cateScrollpane.setSize(getWidth(),getHeight());
        addActor(cateScrollpane);
    }
}