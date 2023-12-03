package kw.artpuzzle.screen;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.kw.gdx.BaseGame;
import com.kw.gdx.screen.BaseScreen;

import java.util.ArrayList;

import kw.artpuzzle.view.ModelGroup;
import kw.artpuzzle.view.ModelGroupTest;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/1 14:36
 */

public class MainScreen extends BaseScreen {
    public MainScreen(BaseGame game) {
        super(game);
        ModelGroupTest t = new ModelGroupTest();
        ArrayList<ModelGroup> allModels = t.getAllModels();
//        Group group = new Group();
//        for (ModelGroup allModel : allModels) {
//            group.addActor(allModel);
//            allModel.setPosition(allModel.getPosX(),allModel.getPosY());
//        }
//        addActor(group);
//        group.setScale(0.4f);
    }
}
