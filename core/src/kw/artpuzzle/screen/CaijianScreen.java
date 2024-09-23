package kw.artpuzzle.screen;

import com.badlogic.gdx.graphics.Texture;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.screen.BaseScreen;

import kw.artpuzzle.group.group.CutGroup;

public class CaijianScreen extends BaseScreen {

    public CaijianScreen(BaseGame game) {
        super(game);

    }

    @Override
    public void initView() {
        super.initView();
        CutGroup group = new CutGroup();
        addActor(group);

    }
}
