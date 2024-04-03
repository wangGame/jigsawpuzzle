package kw.artpuzzle.down;


import com.badlogic.gdx.Gdx;
import com.kw.gdx.net.DownLoadListener;

public class LevelDailyDownload extends BaseDownLoadUtils{
    public LevelDailyDownload(String name, Runnable runnable) {
        super(runnable, new Runnable() {
            @Override
            public void run() {

            }
        });
        this.siteusing = NetContant.url+name;
        String toPath = Gdx.files.getLocalStoragePath();
        String append = append(toPath,"daily/csv/temp/");
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
