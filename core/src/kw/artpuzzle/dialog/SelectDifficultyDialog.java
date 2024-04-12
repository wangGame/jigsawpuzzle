package kw.artpuzzle.dialog;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ArrayMap;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.resource.annotation.ScreenResource;
import com.kw.gdx.resource.cocosload.CocosResource;
import com.kw.gdx.scrollpane.ScrollPane;
import com.kw.gdx.view.dialog.base.BaseDialog;

import kw.artpuzzle.constant.LevelConfig;
import kw.artpuzzle.data.GameData;
import kw.artpuzzle.data.LevelBean;
import kw.artpuzzle.data.SelectItemBean;
import kw.artpuzzle.group.group.SelectDiffItem;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/11 18:26
 */
@ScreenResource("cocos/selectsplitpage.json")
public class SelectDifficultyDialog extends BaseDialog {
    private Runnable runnable;
    private ScrollPane pane;
    public SelectDifficultyDialog(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void show() {
        super.show();
        Group pregroup = dialogGroup.findActor("pregroup");
        LevelBean levelIndex = LevelConfig.levelIndex;
        Actor selectdifficultytitle = findActor("selectdifficultytitle");
        float y = selectdifficultytitle.getY(Align.top);
        float v = Constant.GAMEHIGHT - y - 96 - 126;
        float v1 = Constant.GAMEWIDTH - 60;
        float min = Math.min(v, v1);
        pregroup.setSize(min,min);
        pregroup.setY(1920 - 126 - min / 2.0f + (Constant.GAMEHIGHT - 1920.0f)/2.0f,Align.center);
        pregroup.setX(540.0f,Align.center);
        Texture localTexture = Asset.getAsset().getLocalTexture(
                "finallevel/" + levelIndex.getVersion()
                        + "/" + levelIndex.getLevel_num() + "/"
                        + "pre.png");
        Image preIamge ;
        if (localTexture == null){
            preIamge = new Image(Asset.getAsset().getTexture("61a0bd0f50a6b7268b7113de.png"));
        }else {
            preIamge = new Image(localTexture);
        }

        pregroup.addActor(preIamge);
        preIamge.setSize(pregroup.getWidth(),pregroup.getHeight());
        preIamge.setPosition(pregroup.getWidth()/2.0f,pregroup.getHeight()/2.0f, Align.center);

        pane = new ScrollPane(new Table(){{
            ArrayMap<Integer, SelectItemBean> entries = GameData.getInstance().readSelectItemBean();
            Group group = new Group();
            group.setWidth(Constant.GAMEWIDTH/2.0f);
            add(group);
            for (int i = 0; i < entries.size; i++) {
                SelectItemBean valueAt = entries.getValueAt(i);
                add(new SelectDiffItem(valueAt));
            }
            group = new Group();
            group.setWidth(Constant.GAMEWIDTH/2.0f);
            add(group);
            align(Align.bottom);
            pack();

        }}){
            private boolean update = false;
            @Override
            public void act(float delta) {
                super.act(delta);
                if (isTouchUp()){
                    if (!update) {

//                        temp.set(getX(Align.center),getY(Align.center));
//                        this.getParent().localToStageCoordinates(temp);
//                        if (temp.x > Constant.GAMEWIDTH/2.0f - offsetX && temp.x<Constant.GAMEWIDTH/2.0f + offsetX){
//                            float v = Math.abs(temp.x - Constant.GAMEWIDTH / 2.0f);
//                            setScale(1.0f+0.4f*(Math.abs(v - offsetX)/offsetX));
//                        }else{
//                            setScale(1.0f);
//                        }
                    }
                    update = true;
                }else {
                    update = false;
                }
            }
        };
        pane.setHandeListener(new ScrollPane.HandeListener() {
            private float startX;
            private int page;

            @Override
            public void touchDown() {
                startX = pane.getScrollX();
            }

            @Override
            public void touchUp() {
                pane.setScrollX(page * 220);
            }

            @Override
            public void pan() {
                page = (int) ((pane.getScrollX()) / 220.0f);

            }
        });
        Group itempanel = dialogGroup.findActor("itempanel");
        itempanel.addActor(pane);
        pane.setSize(Constant.GAMEWIDTH,itempanel.getHeight()+100);
        pane.setX(540,Align.center);



        dialogGroup.findActor("btncontinue").addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                runnable.run();
                LevelConfig.splitnum = 1;
                dialogManager.closeDialog(SelectDifficultyDialog.this);
            }
        });

        Group selecttop = findActor("selecttop");
        selecttop.setY(selecttop.getY()+offsetY);
        Actor cancelbtn = selecttop.findActor("cancelbtn");
        cancelbtn.setX(cancelbtn.getX(Align.center) + offsetX,Align.center);
        Actor selectbg = findActor("selectbg");
        selectbg.setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT);
        selectbg.setPosition(540,960,Align.center);

        findActor("cancelbtn").setTouchable(Touchable.enabled);
        findActor("cancelbtn").addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                closeDialog();
            }
        });
    }

    @Override
    public void enterAnimation() {
        dialogGroup.setY(-1920);
        dialogGroup.addAction(Actions.moveToAligned(
                dialogGroup.getX(Align.center),
                960,
                Align.center,
                0.4f
        ));
    }

    @Override
    public void close() {
//        super.close();

        dialogGroup.addAction(
                Actions.sequence(
                        Actions.moveToAligned(
                        dialogGroup.getX(Align.center),
                        0,
                        Align.top,
                        0.4f
                        ),
                        Actions.run(()->{
                            SelectDifficultyDialog.this.remove();
                        })
                )
        );
    }
}
