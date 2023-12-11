package kw.artpuzzle.group.mainview;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ArrayMap;
import com.kw.gdx.scrollpane.ScrollPane;

import kw.artpuzzle.data.CateBean;
import kw.artpuzzle.data.GameData;
import kw.artpuzzle.group.group.CateItemGroup;
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
                CateItemGroup cateGroup = new CateItemGroup(cateBean);
                add(cateGroup).padLeft(10).padRight(10).padTop(28).padBottom(28);
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