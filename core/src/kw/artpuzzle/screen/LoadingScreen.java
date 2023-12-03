package kw.artpuzzle.screen;

import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.screen.BaseScreen;
import com.kw.gdx.sound.AudioProcess;

import kw.artpuzzle.audio.AudioType;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/1 15:48
 */
public class LoadingScreen extends BaseScreen {
    public LoadingScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        AudioProcess.prepare(AudioType.class);
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
