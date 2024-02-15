package kw.artpuzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.net.DownLoad;
import com.kw.gdx.resource.annotation.GameInfo;
import com.kw.gdx.utils.log.NLog;

import kw.artpuzzle.config.GameConfig;
import kw.artpuzzle.constant.GameStaticInstance;
import kw.artpuzzle.constant.LevelConfig;
import kw.artpuzzle.down.DownLevelFileUtils;
import kw.artpuzzle.down.NetContant;
import kw.artpuzzle.listener.GameListener;
import kw.artpuzzle.screen.LoadingScreen;

@GameInfo(width = 1080,height = 1920,batch = Constant.COUPOLYGONBATCH)
public class JigSawPuzzle extends BaseGame {
    public JigSawPuzzle(DownLoad deskDownload, GameListener listener){
        GameStaticInstance.gameListener = listener;
        GameStaticInstance.downLoad = deskDownload;
        new GameConfig();
        listener.changeLocalPath();
    }

    @Override
    protected void loadingView() {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                DownLevelFileUtils utils = new DownLevelFileUtils();
                utils.check();
            }
        });
        setScreen(new LoadingScreen(this));
    }

    @Override
    public void render() {
        Asset.getAsset().assetManagerUpdate();
        super.render();
    }
}
