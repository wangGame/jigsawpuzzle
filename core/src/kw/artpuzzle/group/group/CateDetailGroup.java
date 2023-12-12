package kw.artpuzzle.group.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ArrayMap;
import com.kw.gdx.scrollpane.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.listener.OrdinaryButtonListener;

import kw.artpuzzle.data.CateBean;
import kw.artpuzzle.data.CollectionBean;
import kw.artpuzzle.data.GameData;
import kw.artpuzzle.data.LevelBean;
import kw.artpuzzle.listener.SignListener;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/5 22:31
 */
public class CateDetailGroup extends Group {
    private ScrollPane detailScrollPanel;

    public CateDetailGroup(CateBean cateBean){
        setDebug(true);
        setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT - 140);
        Image bg = new Image(new NinePatch(
                Asset.getAsset().getTexture("white.png"),
                2,2,2,2));
        addActor(bg);
        bg.setSize(getWidth(),getHeight());


        Image back = new Image(Asset.getAsset().getTexture("common/back.png"));
        addActor(back);
        back.setColor(Color.BLACK);
        back.setPosition(72,getHeight() - 72, Align.center);
        back.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                CateDetailGroup.this.remove();
            }
        });


        Label titleLabel = new Label(cateBean.getDesc()+"",new Label.LabelStyle(){{
            font = Asset.getAsset().loadBitFont("cocos/font/inter-semi-32.fnt");
        }});
        titleLabel.setFontScale(1.3f);
        addActor(titleLabel);
        titleLabel.setPosition(getWidth()/2.0f,getHeight() - 72, Align.center);
        titleLabel.setColor(Color.BLACK);
        if (cateBean.getType().equals("COLLECTION")) {
            ArrayMap<Integer, CollectionBean> levelBeanArrayMap
                    = GameData.getInstance().readCollectionCateDetail(cateBean.getDesc());
            detailScrollPanel = new ScrollPane(new Table(){{
                for (int i = 0; i < levelBeanArrayMap.size; i++) {
                    Integer keyAt = levelBeanArrayMap.getKeyAt(i);
                    CollectionBean collectionBean = levelBeanArrayMap.get(keyAt);
                    BannerGroup group = new BannerGroup(collectionBean);
                    add(group);
                    row();
                }
                pack();
            }});
        }else {
            ArrayMap<Integer, LevelBean> levelBeanArrayMap
                    = GameData.getInstance().readCateDetail(cateBean.getDesc());
            detailScrollPanel = new ScrollPane(new Table() {{
                for (int i = 1; i <= levelBeanArrayMap.size; i++) {
                    LevelBean valueAt = levelBeanArrayMap.getValueAt(i - 1);
                    ItemGroup itemGroup = new ItemGroup(valueAt, new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    itemGroup.setType(cateBean.getType());
                    add(itemGroup).pad(15);
                    if (i % 2 == 0) {
                        row();
                    }
                }
                align(Align.top);
            }});
        }
        detailScrollPanel.setSize(getWidth(), getHeight() - 142.0f);
        addActor(detailScrollPanel);
    }
}
