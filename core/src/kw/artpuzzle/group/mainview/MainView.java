package kw.artpuzzle.group.mainview;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.kw.gdx.listener.OrdinaryButtonListener;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/4 19:54
 */
public class MainView extends BaseView {
    public MainView(Runnable runnable){
        addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //open
                runnable.run();
            }
        });
    }
}
