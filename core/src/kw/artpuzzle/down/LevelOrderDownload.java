package kw.artpuzzle.down;


import com.badlogic.gdx.Gdx;
import com.kw.gdx.BaseGame;
import com.kw.gdx.net.DownLoadListener;

public class LevelOrderDownload  extends BaseDownLoadUtils{
    public LevelOrderDownload(BaseGame game, String name
            , Runnable runnable) {
        super(runnable, new Runnable() {
            @Override
            public void run() {
                System.out.println("-------------------------");
            }
        });
        this.siteusing = NetContant.url;
        this.fromPath = "csv/"+name;
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
