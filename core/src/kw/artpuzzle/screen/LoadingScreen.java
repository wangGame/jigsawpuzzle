package kw.artpuzzle.screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.net.DownLoad;
import com.kw.gdx.resource.annotation.ScreenResource;
import com.kw.gdx.screen.BaseScreen;
import com.kw.gdx.sound.AudioProcess;

import kw.artpuzzle.audio.AudioType;
import kw.artpuzzle.data.GameData;
import kw.artpuzzle.data.LevelBean;
import kw.artpuzzle.down.DownLevelUtils;
import kw.artpuzzle.down.GameStaticInstance;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/1 15:48
 */
@ScreenResource("cocos/loadscreen.json")
public class LoadingScreen extends BaseScreen {
    public LoadingScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        AudioProcess.prepare(AudioType.class);
        Actor loadingbg = rootView.findActor("loadingbg");
        loadingbg.setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT);
        loadingbg.setPosition(540.0f,960.0f, Align.center);
        GameData.loadLevel();

//
//        LevelBean levelBean = new LevelBean();
//        levelBean.setLevelId("1");
//        levelBean.setSortId(1);
//        levelBean.setVersion("version1");
//        DownLevelUtils downLevelUtils
//                = new DownLevelUtils(levelBean, "level/out/", new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//        downLevelUtils.downLoad();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Asset.getAsset().update();
        if (Asset.getAsset().getProcess()>=1.0f){
            AudioProcess.loadFinished();
            setScreen(MainScreen.class);
        }
    }
}
