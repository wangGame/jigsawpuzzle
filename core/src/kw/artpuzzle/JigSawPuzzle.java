package kw.artpuzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.resource.annotation.GameInfo;
import com.kw.gdx.utils.log.NLog;
import kw.artpuzzle.screen.LoadingScreen;

@GameInfo(width = 720,height = 1280,batch = Constant.COUPOLYGONBATCH)
public class JigSawPuzzle extends BaseGame {
    public JigSawPuzzle(){
        Gdx.isJiami = true;
        Asset.enterGameClear();
        NLog.isLog = false;
        Constant.viewColor.set(Color.BLACK);
    }

    @Override
    protected void loadingView() {
        setScreen(new LoadingScreen(this));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
}
