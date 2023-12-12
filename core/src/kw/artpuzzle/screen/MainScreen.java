package kw.artpuzzle.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.BaseGame;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.resource.annotation.ScreenResource;
import com.kw.gdx.screen.BaseScreen;
import com.kw.gdx.scrollpane.ScrollPane;

import kw.artpuzzle.data.CateBean;
import kw.artpuzzle.constant.GameStaticInstance;
import kw.artpuzzle.group.game.GameView;
import kw.artpuzzle.group.group.CateDetailGroup;
import kw.artpuzzle.group.mainview.CateView;
import kw.artpuzzle.group.mainview.DailyView;
import kw.artpuzzle.group.mainview.MainView;
import kw.artpuzzle.group.mainview.PuzzleView;
import kw.artpuzzle.listener.SignListener;
import kw.artpuzzle.utils.DailyUtils;
import kw.artpuzzle.utils.DateBean;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/1 14:36
 */
@ScreenResource("cocos/mainscreen.json")
public class MainScreen extends BaseScreen {
    private ScrollPane mainScrollPane;
    private int pageIndex;
    private Group content;
    public MainScreen(BaseGame game) {
        super(game);
        GameStaticInstance.baseScreen = this;
    }

    @Override
    public void initView() {
        super.initView();
        content = rootView.findActor("content");
        rootView.findActor("maintop").setTouchable(Touchable.childrenOnly);
        content.setTouchable(Touchable.childrenOnly);
        mainScrollPane = new ScrollPane(new Table(){{
            add(new MainView(runnable));
            add(new DailyView(runnable));
            add(new CateView(new SignListener(){
                @Override
                public void sign(Object o) {
                    if (o instanceof CateBean){
                        CateBean cateName = (CateBean)o;
                        CateDetailGroup cateDetailGroup = new CateDetailGroup(cateName);
                        cateDetailGroup.setY(1920 + offsetY ,Align.top);
                        content.addActor(cateDetailGroup);
                    }
                }
            }));
            add(new PuzzleView());
            pack();
        }});
        mainScrollPane.setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT - 142 - 142);
        mainScrollPane.setPosition(540.0f,960.0f,Align.center);
        mainScrollPane.setRectangle(0,0,0,0);
        content.addActor(mainScrollPane);
        Actor maintop = rootView.findActor("maintop");
        maintop.setY(maintop.getY()+offsetY);
        Actor fontgroup = rootView.findActor("fontgroup");
        fontgroup.setY(fontgroup.getY()+offsetY);
        Actor mainbottom = rootView.findActor("mainbottom");
        mainbottom.setY(mainbottom.getY()-offsetY);
    }

    @Override
    protected void initListener() {
        super.initListener();
        Group librarybtn = rootView.findActor("librarybtn");
        Group dailybtn = rootView.findActor("dailybtn");
        Group categorybtn = rootView.findActor("categorybtn");
        Group collectbtn = rootView.findActor("collectbtn");
        extracted(librarybtn,0);
        extracted(dailybtn,1);
        extracted(categorybtn,2);
        extracted(collectbtn,3);
        updatePage(librarybtn);
        Label dailynum = dailybtn.findActor("dailynum");
        if (dailynum!=null) {
            DateBean dateBean = DailyUtils.currentDateBean();
            dailynum.setText(dateBean.getDay());
        }
    }

    public void resetBtn(){
        Group librarybtn = rootView.findActor("librarybtn");
        Group dailybtn = rootView.findActor("dailybtn");
        Group categorybtn = rootView.findActor("categorybtn");
        Group collectbtn = rootView.findActor("collectbtn");
//        868e9d
        changeColor(librarybtn,"#868e9d");
        changeColor(dailybtn,"#868e9d");
        changeColor(categorybtn,"#868e9d");
        changeColor(collectbtn,"#868e9d");
        dailybtn.findActor("dailynum").setColor(Color.valueOf("#868e9d"));
    }

    public void changeColor(Group librarybtn,String color){
        Actor cateIcon = librarybtn.findActor("cateIcon");
        cateIcon.setColor(Color.valueOf(color));
        Actor btnlabel = librarybtn.findActor("btnlabel");
        btnlabel.setColor(Color.valueOf(color));
    }

    private void extracted(Group librarybtn,int page) {
        librarybtn.setOrigin(Align.center);
        librarybtn.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                pageIndex = page;
                updatePage(librarybtn);
            }
        });
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            GameView gameView = new GameView(MainScreen.this);
            gameView.initView();
            stage.addActor(gameView);
        }
    };

    public void updatePage(Group librarybtn){
        resetBtn();
        mainScrollPane.setScrollX(Constant.GAMEWIDTH * pageIndex);
        mainScrollPane.updateVisualScroll();
        changeColor(librarybtn,"#30ca58");
        Label dailynum = librarybtn.findActor("dailynum");
        if (dailynum!=null) {
            dailynum.setColor(Color.valueOf("#30ca58"));
        }
    }
}
