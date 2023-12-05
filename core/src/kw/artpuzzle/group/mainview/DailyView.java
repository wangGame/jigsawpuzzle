package kw.artpuzzle.group.mainview;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.scrollpane.ScrollPane;

import java.util.logging.Level;

import kw.artpuzzle.group.group.ItemGroup;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/4 21:30
 */
public class DailyView extends BaseView {
    private ScrollPane scrollPane;
    public DailyView(){
        scrollPane = new ScrollPane(new Table(){{
//            Group titleGrop = new Group();
//            titleGrop.setSize(Constant.GAMEWIDTH,100);
//            add(titleGrop);
//            row();
            for (int yy = 0; yy < 30; yy++) {

                for (int i = 0; i < 2; i++) {

                    add(new Group(){{
                        Label label = new Label("",new Label.LabelStyle(){{
                            font = Asset.getAsset().loadBitFont("cocos/font/inter-semi-32.fnt");
                        }});
                        addActor(label);
                        label.setText("2023 11");
                        label.pack();
                        setSize(465,50);
                        label.setPosition(465.0f/2.0f,25, Align.center);
                        setDebug(true);
                    }}).pad(15);
                }
                row();
                for (int i = 0; i < 4; i++) {
                    add(new ItemGroup()).pad(15);
                    add(new ItemGroup()).pad(15);
                    row();
                }

            }
        }});
        scrollPane.setSize(getWidth(),getHeight());
        addActor(scrollPane);
    }
}