package kw.artpuzzle.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kw.gdx.asset.Asset;

import java.util.ArrayList;

public class ModelUtils {
    private ArrayList<ModelGroup> allModels;
    private TempView tempView;
    public ModelUtils(String targetTextureName,int row,int colomn){
        allModels = new ArrayList<>();
        Texture texture = Asset.getAsset().getTexture(targetTextureName);
        int width = texture.getWidth();
        int height = texture.getHeight();
        int modelIndex = -1;
        tempView = new TempView();
        for (int i3 = 0; i3 < row; i3++) {
            for (int i4 = 0; i4 < colomn; i4++) {
                modelIndex ++;
                ModelGroup maskImage = new ModelGroup("out1/"+modelIndex+".png");
                maskImage.setTexure(texture);
                maskImage.setName(""+modelIndex);
                Actor actor = tempView.findActor(modelIndex+"");
                maskImage.setImageSize(actor.getWidth(), actor.getHeight());
                maskImage.setPosX(actor.getX()+actor.getWidth()/4.0f);
                maskImage.setPosY(actor.getY()+actor.getWidth()/4.0f);
                maskImage.setStartU((actor.getX())/width);
                maskImage.setStartV(1.0f-(actor.getY()+actor.getWidth())/height);
                allModels.add(maskImage);
            }
        }
    }

    public ArrayList<ModelGroup> getAllModels() {
        return allModels;
    }

    public TempView getTempView() {
        return tempView;
    }
}