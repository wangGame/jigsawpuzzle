package kw.artpuzzle.screen;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.screen.BaseScreen;
import com.kw.gdx.scrollpane.ScrollPane;

import java.util.ArrayList;

import kw.artpuzzle.group.GameView;
import kw.artpuzzle.view.ModelGroup;
import kw.artpuzzle.view.ModelGroupTest;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/1 14:36
 */

public class MainScreen extends BaseScreen {
    public MainScreen(BaseGame game) {
        super(game);
        GameView view = new GameView();
        view.init();
        addActor(view);
    }
}
