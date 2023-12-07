package kw.artpuzzle.group.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.scrollpane.ScrollPane;

import java.util.ArrayList;

import kw.artpuzzle.listener.MyClickListener;
import kw.artpuzzle.utils.GameLogicUtils;
import kw.artpuzzle.view.ModelGroup;
import kw.artpuzzle.view.ModelUtils;
import kw.artpuzzle.view.TempView;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/4 10:37
 */
public class GameView extends Group {
    private ScrollPane bottomPanelScrollPanel;
    private GameLogicUtils logicUtils;
    private Image gameViewBg;
    private ModelUtils modelUtils ;
    public void initView(){
        setSize(1080,1920);
        this.gameViewBg = new Image(new NinePatch(Asset.getAsset().getTexture("white.png")));
        addActor(gameViewBg);
        gameViewBg.setColor(Color.valueOf("#AAAAAA"));
        gameViewBg.setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT);
        gameViewBg.setPosition(540,960,Align.center);
        modelUtils = new ModelUtils("123.jpg",6,6);
        ArrayList<ModelGroup> allModels = modelUtils.getAllModels();
        logicUtils = new GameLogicUtils(modelUtils.getTempView());
        bottomPanelScrollPanel = new ScrollPane(new Table(){{
            for (ModelGroup allModel : allModels) {
                allModel.addListener(getItemListener(allModel));
                add(allModel);
            }
            pack();
            align(Align.bottom);
            setY(100);
        }});
        bottomPanelScrollPanel.setTouchable(Touchable.childrenOnly);
        bottomPanelScrollPanel.setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT);
        bottomPanelScrollPanel.setDebug(true);
        bottomPanelScrollPanel.setY(100);
        TempView view = modelUtils.getTempView();
        addActor(view);
        view.setOrigin(Align.center);
        view.setScale(0.5f);
        view.addListener(new ClickListener(){
            private Vector2 initialPointer1 = new Vector2();
            private Vector2 initialPointer2 = new Vector2();
            float initialDistance;

            private Vector2 pointer1 =  new Vector2();
            private Vector2 pointer2 =  new Vector2();
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (pointer == 0) {
                    initialPointer1.set(x, y);
                } else if (pointer == 1) {
                    initialPointer2.set(x, y);
                    initialDistance = initialPointer1.dst(initialPointer2);
                }
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
                pointer1.set(x, y);
                pointer2.set(initialPointer2);
                float distance = pointer1.dst(pointer2);
                float scale = distance / initialDistance;
                setScale(scale);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println(":------------------------------");
            }
        });
        view.setPosition(Constant.GAMEWIDTH/2.0f,(Constant.GAMEHIGHT - 300)/2.0f + 300,Align.center);
        addActor(bottomPanelScrollPanel);
        bottomPanelScrollPanel.setRectangle(0,0,getWidth(),220);
        Texture texture = modelUtils.getTexture();

//        Group group = new Group();
//        for (ModelGroup allModel : allModels) {
//            allModel.addListener(getItemListener(allModel));
//            group.addActor(allModel);
//            allModel.setPosition(allModel.getPosX(),allModel.getPosY(),Align.center);
//        }
//        addActor(group);
//        group.setPosition(200,200);
//        group.setScale(0.4f);
    }

    private boolean successMove = false;
    private MyClickListener getItemListener(ModelGroup allModel) {
        return new MyClickListener(allModel){
            private Vector2 startV = new Vector2();
            private Vector2 convert = new Vector2();
            private Vector2 targetPos ;
            private boolean isDragged = false;
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                successMove = false;
                isDragged = false;
                startV.set(x,y);
                bottomPanelScrollPanel.setValid(true);
                ModelGroup group = getGroup();
                targetPos = logicUtils.findTargetPos(group.getName());
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
                if (successMove)return;
                if (isDragged || ((Math.abs(x - startV.x) < Math.abs(y - startV.y))&&(Math.abs(x - startV.x) * Math.abs(x - startV.x) +
                        Math.abs(y - startV.y) * Math.abs(y - startV.y)>200))){
                    isDragged = true;
                    ModelGroup group = getGroup();
                    convert.set(x,y);
                    bottomPanelScrollPanel.cancel();
                    group.localToStageCoordinates(convert);
                    group.setImagePosition(x,y);
                    Vector2 vector2 = group.imageVector();
                    logicUtils.convertTarget(vector2);
                    if (logicUtils.check(targetPos,vector2)) {
                        successMove = true;
                        group.addAction(Actions.moveToAligned(targetPos.x,targetPos.y,Align.center,0.1f));
                        Vector2 vector21 = group.imageVector();
                        logicUtils.convertTarget(vector21);
                        group.resetPosition();
                        group.setX(vector21.x,Align.center);
                        group.setY(vector21.y,Align.center);
                        group.clearListeners();
                        group.setImageScale(1.0f);
                        logicUtils.addActor(group);
                    }
                }else {
                    return;
                }
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (successMove)return;
                ModelGroup group = getGroup();
                group.resetPosition();
                group.resetScale();
            }
        };
    }
}
