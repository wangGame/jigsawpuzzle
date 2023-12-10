package kw.artpuzzle.group.group;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/10 14:15
 */
public class ThemeItem extends Group {
    private String themeName;
    public ThemeItem(String themeName){
        this.themeName = themeName;
        setSize(358,358);
        Texture texture = Asset.getAsset().getTexture("themebg/" + themeName);
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        Image image = new Image(texture);
        addActor(image);
        image.setSize(getWidth(),getHeight());
        Image iconSelect = new Image(Asset.getAsset().getTexture("common/iconselect.png"));
        addActor(iconSelect);
        iconSelect.setPosition(getWidth() - 20,getHeight() - 20 , Align.topRight);
    }

    public String getThemeIndex() {
        return themeName;
    }
}
