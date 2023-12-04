package kw.artpuzzle.screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.BaseGame;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.resource.annotation.ScreenResource;
import com.kw.gdx.screen.BaseScreen;
import com.kw.gdx.scrollpane.ScrollPane;

import kw.artpuzzle.group.game.GameView;
import kw.artpuzzle.group.mainview.CateView;
import kw.artpuzzle.group.mainview.DailyView;
import kw.artpuzzle.group.mainview.MainView;
import kw.artpuzzle.group.mainview.PuzzleView;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/1 14:36
 */
@ScreenResource("cocos/mainscreen.json")
public class MainScreen extends BaseScreen {
    private ScrollPane mainScrollPane;
    private int pageIndex;
    public MainScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        mainScrollPane = new ScrollPane(new Table(){{
            add(new MainView(runnable));
            add(new DailyView());
            add(new CateView());
            add(new PuzzleView());
            pack();
        }});
        mainScrollPane.setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT - 142 - 142);
        mainScrollPane.setPosition(540.0f,960.0f,Align.center);
        mainScrollPane.setRectangle(0,0,0,0);
        rootView.addActor(mainScrollPane);
    }

    @Override
    protected void initAnnotation() {
        super.initAnnotation();
        Actor librarybtn = rootView.findActor("librarybtn");
        Actor dailybtn = rootView.findActor("dailybtn");
        Actor categorybtn = rootView.findActor("categorybtn");
        Actor collectbtn = rootView.findActor("collectbtn");
        librarybtn.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                pageIndex = 0;
                updatePage();
            }
        });
        dailybtn.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                pageIndex = 1;
                updatePage();
            }
        });
        categorybtn.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                pageIndex = 2;
                updatePage();
            }
        });
        collectbtn.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                pageIndex = 3;
                updatePage();
            }
        });
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            GameView gameView = new GameView();
            gameView.initView();
            stage.addActor(gameView);
        }
    };

    public void updatePage(){
        mainScrollPane.setScrollX(Constant.GAMEWIDTH * pageIndex);
        mainScrollPane.updateVisualScroll();
    }
}
