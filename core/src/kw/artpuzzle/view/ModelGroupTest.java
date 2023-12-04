package kw.artpuzzle.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;

import java.util.ArrayList;

public class ModelGroupTest{
    private ArrayList<ModelGroup> allModels;
    private TempView view;
    public ModelGroupTest(){
        allModels = new ArrayList<>();
        Texture texture = Asset.getAsset().getTexture("animals_426.jpg");
        int width = texture.getWidth();
        int height = texture.getHeight();
        int i1 = width / 6;
        int i2 = height / 6;
        float startX = 0.0f;
        float startY = 0.0f;
        int in = -1;
        view = new TempView();
        for (int i3 = 0; i3 < 6; i3++) {
            startX = 0;
            for (int i4 = 0; i4 < 6; i4++) {
                in ++;
                String picName = in+"";
                ModelGroup maskImage = new ModelGroup("out1/"+picName+".png");
                maskImage.setName(""+picName);
//                group.addActor(maskImage);
                // 磨具/磨具宽（高）  == i1（i2）/游戏宽高
                Actor actor = view.findActor(picName);
                maskImage.setImageSize(actor.getWidth(), actor.getHeight());
                maskImage.setPosX(actor.getX()+actor.getWidth()/4.0f);
                maskImage.setPosY(actor.getY()+actor.getWidth()/4.0f);
                //startX（starY） / 游戏宽（高）
                maskImage.setStartU((actor.getX())/width);
                startX = startX + i1;
                maskImage.setStartV(1.0f-(actor.getY()+actor.getWidth())/height);
                allModels.add(maskImage);
                //i1（i2）/游戏宽
            }
            startY = startY + i2;
        }
    }

    public ArrayList<ModelGroup> getAllModels() {
        return allModels;
    }

    public TempView getView() {
        return view;
    }
}