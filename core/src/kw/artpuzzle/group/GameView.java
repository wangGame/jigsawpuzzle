package kw.artpuzzle.group;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.scrollpane.ScrollPane;

import java.util.ArrayList;

import kw.artpuzzle.view.ModelGroup;
import kw.artpuzzle.view.ModelGroupTest;
import kw.artpuzzle.view.TempView;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/4 10:37
 */
public class GameView extends Group {
    private ScrollPane pane;
    private PlayTargetView view;
    public void init(){
        ModelGroupTest t = new ModelGroupTest();
        ArrayList<ModelGroup> allModels = t.getAllModels();
        view = new PlayTargetView(t.getView());
        pane = new ScrollPane(new Table(){{
            for (ModelGroup allModel : allModels) {
                allModel.addListener(getItemListener(allModel));
                add(allModel);
            }
            pack();
            align(Align.bottom);
            setY(100);
        }});
        pane.setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT);
        pane.setDebug(true);
        pane.setY(100);
        TempView view = t.getView();
        addActor(view);
        view.setOrigin(Align.center);
        view.setScale(0.5f);
        view.setPosition(Constant.GAMEWIDTH/2.0f,(Constant.GAMEHIGHT - 300)/2.0f + 300,Align.center);
        addActor(pane);
    }

    private boolean successMove = false;
    private MyClickListener getItemListener(ModelGroup allModel) {
        return new MyClickListener(allModel){
            private Vector2 startV = new Vector2();
            private Vector2 convert = new Vector2();
            private Vector2 targetPos ;
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                successMove = false;
                startV.set(x,y);
                pane.setValid(true);
                ModelGroup group = getGroup();
                targetPos = view.findTargetPos(group.getName());
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
                if (successMove)return;

                if (Math.abs(x - startV.x) * Math.abs(x - startV.x) + Math.abs(y - startV.y) * Math.abs(y - startV.y)>20){
                    ModelGroup group = getGroup();
                    convert.set(x,y);
                    pane.cancel();
                    group.localToStageCoordinates(convert);
                    group.setImagePosition(x,y);
                    Vector2 vector2 = group.imageVector();
                    view.convertTarget(vector2);
                    if (view.check(targetPos,vector2)) {
                        successMove = true;
                        group.addAction(Actions.moveToAligned(targetPos.x,targetPos.y,Align.center,0.2f));
                        Vector2 vector21 = group.imageVector();
                        view.convertTarget(vector21);
                        group.resetPosition();
                        group.setX(vector21.x,Align.center);
                        group.setY(vector21.y,Align.center);
                        group.clearListeners();
                        group.setImageScale(1.0f);
                        view.addActor(group);
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
