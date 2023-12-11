package kw.artpuzzle.dialog;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ArrayMap;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.resource.annotation.ScreenResource;
import com.kw.gdx.resource.cocosload.CocosResource;
import com.kw.gdx.scrollpane.ScrollPane;
import com.kw.gdx.view.dialog.base.BaseDialog;

import kw.artpuzzle.constant.LevelConfig;
import kw.artpuzzle.data.GameData;
import kw.artpuzzle.data.LevelBean;
import kw.artpuzzle.data.SelectItemBean;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/11 18:26
 */
@ScreenResource("cocos/selectsplitpage.json")
public class SelectDifficultyDialog extends BaseDialog {
    @Override
    public void show() {
        super.show();
        Group pregroup = dialogGroup.findActor("pregroup");
        LevelBean levelIndex = LevelConfig.levelIndex;
        Image preIamge = new Image(Asset.getAsset().getLocalTexture(
                "finallevel/"+levelIndex.getVersion()
                        +"/"+levelIndex.getLevelUUID()+"/"
                        +levelIndex.getLevelUUID()+".png"));
        pregroup.addActor(preIamge);
        preIamge.setSize(pregroup.getWidth(),pregroup.getHeight());
        preIamge.setPosition(pregroup.getWidth()/2.0f,pregroup.getHeight()/2.0f, Align.center);

        ScrollPane pane = new ScrollPane(new Table(){{
            ArrayMap<Integer, SelectItemBean> entries = GameData.getInstance().readSelectItemBean();
        }});
        Group itempanel = dialogGroup.findActor("itempanel");
        itempanel.addActor(pane);
        pane.setSize(Constant.GAMEWIDTH,itempanel.getHeight());
    }
}