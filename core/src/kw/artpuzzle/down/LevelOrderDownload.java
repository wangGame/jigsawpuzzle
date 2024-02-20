package kw.artpuzzle.down;


import com.badlogic.gdx.Gdx;
import com.kw.gdx.BaseGame;
import com.kw.gdx.net.DownLoadListener;

public class LevelOrderDownload  extends BaseDownLoadUtils{
    public LevelOrderDownload(String name, Runnable runnable) {
        super(runnable, new Runnable() {
            @Override
            public void run() {

            }
        });
        this.siteusing = NetContant.url+"csv/"+name;
        String toPath = Gdx.files.getLocalStoragePath();
        String append = append(toPath,"csv/temp/");
        this.toPath = append+name;
        this.success = new DownLoadListener() {
            @Override
            public void downLoadCallBack() {
                super.downLoadCallBack();
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        runnable.run();
                    }
                });
            }
        };
        this.failed = new DownLoadListener() {
            @Override
            public void downLoadCallBack() {
                super.downLoadCallBack();
            }
        };
    }
}
