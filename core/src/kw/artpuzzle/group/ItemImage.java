package kw.artpuzzle.group;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.resource.cocosload.CocosResource;
import com.kw.gdx.zip.PackZip;

import kw.artpuzzle.data.LevelBean;
import kw.artpuzzle.down.DownLevelUtils;
import kw.artpuzzle.listener.LevelListener;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/8 18:30
 */
public class ItemImage extends Group {
    private LevelBean levelBean;
    private String levelPath;
    private String localStoragePath;
    public ItemImage(LevelBean levelBean){
        setSize(465.00f,465.00f);
        this.levelBean = levelBean;
        localStoragePath = Gdx.files.getLocalStoragePath();
        levelPath = "finallevel/" + levelBean.getVersion() + "/" + levelBean.getLevelUUID();
        if (Gdx.files.local(levelPath).exists()){
            if (PackZip.check(localStoragePath + levelPath)) {
                Image levelImage = new Image(Asset.getAsset().getLocalTexture(levelPath+"/"+levelBean.getLevelUUID()+".png"));
                addActor(levelImage);
                levelImage.setSize(getWidth(),getHeight());
            }else {
                FileHandle local = Gdx.files.local(levelPath);
                local.deleteDirectory();
                downLoadImage();
            }
        }else {
            FileHandle local = Gdx.files.local(levelPath);
            local.deleteDirectory();
            downLoadImage();
        }
    }

    public void downLoadImage(){
        DownLevelUtils downLevelUtils
                = new DownLevelUtils(levelBean, "level/out/", new Runnable() {
            @Override
            public void run() {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        if (PackZip.check(localStoragePath + levelPath)) {
                            Image levelImage = new Image(Asset.getAsset().getLocalTexture(levelPath+"/"+levelBean.getLevelUUID()+".png"));
                            addActor(levelImage);
                            levelImage.setSize(getWidth(),getHeight());
                        }
                    }
                });
            }
        }, new Runnable() {
            @Override
            public void run() {

            }
        });
        downLevelUtils.downLoad();
    }
}
