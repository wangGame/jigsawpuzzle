package kw.artpuzzle.group.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ArrayMap;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.resource.cocosload.CocosResource;
import com.kw.gdx.screen.BaseScreen;
import com.kw.gdx.scrollpane.ScrollPane;

import java.util.ArrayList;
import java.util.Collections;

import kw.artpuzzle.constant.LevelConfig;
import kw.artpuzzle.data.GameData;
import kw.artpuzzle.data.LevelBean;
import kw.artpuzzle.group.group.BgTheme;
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
    private Group rootView;
    private ScrollPane bottomPanelScrollPanel;
    private GameLogicUtils logicUtils;
    private ModelUtils modelUtils ;
    private  BaseScreen baseScreen;
    private boolean isShowPre;
    private Table bottomModelTable;
    //因为排序
    private ArrayList<ModelGroup> finalModelGroup;
    private boolean showBorder;

    public GameView(BaseScreen baseScreen){
       this.baseScreen = baseScreen;
       this.finalModelGroup = new ArrayList<>();
    }

    public void initView(){
        setSize(1080,1920);
        setPosition(Constant.GAMEWIDTH/2.0f,Constant.GAMEHIGHT/2.0f,Align.center);
        rootView = CocosResource.loadFile("cocos/gamegroup.json");
        addActor(rootView);
        Actor gamebg = rootView.findActor("gamebg");
        gamebg.setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT);
        gamebg.setPosition(540.0f,960.0f,Align.center);
        rootView.setPosition(getWidth()/2.0f,getHeight()/2.0f,Align.center);

        rootView.findActor("gamebg").setColor(Color.valueOf("#C0C3C8"));

        Group gamebottom = rootView.findActor("gamebottom");
        gamebottom.setY(gamebottom.getY() - baseScreen.getOffsetY());

        Group gamemiddle = rootView.findActor("gamemiddle");
        Actor middlebg = gamemiddle.findActor("middlebg");
        middlebg.setVisible(false);
        Vector2 vector2 = new Vector2();
        vector2.set(middlebg.getX(Align.center),middlebg.getY(Align.center));
        middlebg.setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT - 140.0f - 260.0f);
        middlebg.setPosition(vector2,Align.center);
        Group gametop = rootView.findActor("gametop");
        gametop.setY(gametop.getY() + baseScreen.getOffsetY());
        Actor topback = gametop.findActor("topback");
        Actor egebtn = gametop.findActor("egebtn");
        Actor clearbtn = gametop.findActor("clearbtn");
        Actor tipbtn = gametop.findActor("tipbtn");
        Actor prebtn = gametop.findActor("prebtn");
        Actor themebtn = gametop.findActor("themebtn");
        ArrayList<Actor> actors = new ArrayList<>();
        actors.add(topback);
        actors.add(egebtn);
        actors.add(clearbtn);
        actors.add(tipbtn);
        actors.add(prebtn);
        actors.add(themebtn);
        topback.setOrigin(Align.center);
        egebtn.setOrigin(Align.center);
        clearbtn.setOrigin(Align.center);
        tipbtn.setOrigin(Align.center);
        prebtn.setOrigin(Align.center);
        themebtn.setOrigin(Align.center);

        float v = Constant.GAMEWIDTH / 6.0f;
        float v1 = v / 2.0f;
        for (int i = 0; i < actors.size(); i++) {
            Actor actor = actors.get(i);
            actor.setX(v1 + v * i,Align.center);
        }
        LevelBean levelBean = LevelConfig.levelIndex;
        modelUtils = new ModelUtils("finallevel/"
                +levelBean.getVersion()+"/"+levelBean.getLevelUUID()
                +"/"+levelBean.getLevelUUID()+".png",6,6);
        ArrayList<ModelGroup> allModels = modelUtils.getAllModels();
        finalModelGroup.addAll(allModels);
        Collections.shuffle(finalModelGroup);
        logicUtils = new GameLogicUtils(modelUtils.getTempView());

        bottomPanelScrollPanel = new ScrollPane(bottomModelTable = new Table(){{
            for (ModelGroup allModel : finalModelGroup) {
                allModel.addListener(getItemListener(allModel));
                add(allModel);
                allModel.setUpdateSize(new Runnable(){

                    @Override
                    public void run() {

                    }
                });
            }
            pack();
            align(Align.bottomLeft);
            setY(100);
        }});
        bottomPanelScrollPanel.setTouchable(Touchable.childrenOnly);
        bottomPanelScrollPanel.setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT);
        bottomPanelScrollPanel.setDebug(true);
        bottomPanelScrollPanel.setY(50);
        gamebottom.addActor(bottomPanelScrollPanel);

        Group picGroup = new Group();
        picGroup.setSize(1050,1050);
        TempView view = modelUtils.getTempView();
        picGroup.addActor(view);
        gamemiddle.addActor(picGroup);
        view.setOrigin(Align.center);
        view.setScale(0.5f);
        picGroup.setPosition(gamemiddle.getWidth()/2.0f,gamemiddle.getHeight()/2.0f,Align.center);
        setOrigin(Align.center);
        gamemiddle.addListener(new ActorGestureListener(){
            private float touchDownScale;
            private float minScale;
            private float maxScale;
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                touchDownScale = view.getScaleX();
                minScale = (1080.0f * 0.6f) / view.getWidth();
                maxScale = (1080.0f * 1.2f) / view.getWidth();
            }

            @Override
            public void zoom(InputEvent event, float initialDistance, float distance) {
                super.zoom(event, initialDistance, distance);
                System.out.println(minScale +" ------- "+maxScale);
                float v = distance / initialDistance;
                float v1 = v *touchDownScale;
                if (maxScale<v1){
                    v1 = maxScale;
                }
                if (minScale>v1){
                    v1 = minScale;
                }
                view.setScale(v1);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);

            }
        });
        view.setPosition(picGroup.getWidth()/2.0f,picGroup.getHeight()/2.f,Align.center);
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
        topback.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GameView.this.addAction(
                        Actions.sequence(
                                Actions.fadeOut(0.2f),
                                Actions.removeActor()));
            }
        });
        egebtn.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                showBorder();
            }
        });
//        gameSuccess(picGroup,view);
        prebtn.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                isShowPre = !isShowPre;
                //绘制
                view.setShowPre(isShowPre);
            }
        });

        themebtn.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                BgTheme bgTheme = new BgTheme();
                addActor(bgTheme);

            }
        });
    }

    private void gameSuccess(Group picGroup,TempView view) {
        Actor gametop1 = rootView.findActor("gametop");
        Actor gamebottom1 = rootView.findActor("gamebottom");
        gamebottom1.setVisible(false);
        gametop1.setVisible(false);
        view.setScale(0.56f);
//        view.addAction(Actions.scaleTo(0.52f,0.52f,0.2f));
        Image image = new Image(new NinePatch(
                Asset.getAsset().getTexture("common/success_border.png"),
                16,16,16,16));
        picGroup.addActor(image);
        image.setSize(1050,1050);
        image.setPosition(picGroup.getWidth()/2.0f,picGroup.getHeight()/2.0f,Align.center);
        picGroup.setOrigin(Align.center);
        picGroup.addAction(Actions.sequence(
                Actions.delay(2,
                        Actions.scaleTo(0.798f,0.798f,0.3f))));

        Group group = CocosResource.loadFile("cocos/btn.json");
        addActor(group);
        group.setPosition(getWidth()/2.0f,200 - baseScreen.getOffsetY(),Align.bottom);
        group.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                GameView.this.addAction(Actions.sequence(
                        Actions.fadeOut(0.4f),
                        Actions.removeActor()
                ));
            }
        });
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
                        finalModelGroup.remove(group);
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

    public void showBorder() {
        showBorder = !showBorder;
        bottomModelTable.clear();
        for (ModelGroup modelGroup : finalModelGroup) {
            if (showBorder) {
                ArrayList<ModelGroup> borderModels = modelUtils.getBorderModels();
                if (borderModels.contains(modelGroup)) {
                    bottomModelTable.add(modelGroup);
                }
            }else {
                bottomModelTable.add(modelGroup);
            }
        }
        bottomModelTable.pack();
        bottomModelTable.align(Align.bottomLeft);
    }
}
