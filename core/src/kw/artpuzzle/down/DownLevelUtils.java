package kw.artpuzzle.down;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.kw.gdx.BaseGame;
import com.kw.gdx.net.DownLoadListener;
import com.kw.gdx.zip.PackZip;

import kw.artpuzzle.data.LevelBean;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/8 15:56
 */
public class DownLevelUtils extends BaseDownLoadUtils{
    protected String outPath;
    protected LevelBean levelBean;
    public DownLevelUtils(LevelBean levelBean, String outPath,Runnable successRunnable
            , Runnable reTryRunnable) {
        super(successRunnable,reTryRunnable);
        this.outPath = outPath;
        this.levelBean = levelBean;
        this.siteusing = NetContant.url
                +levelBean.getVersion()
                +"/"+levelBean.getLevelId()+".zip";
        this.toPath = outPath+levelBean.getLevelId()+".zip";
    }

    protected void downFile() {
        success = new DownLoadListener() {
            @Override
            public void downLoadCallBack() {
                super.downLoadCallBack();
                new Thread() {
                    @Override
                    public void run() {
                        String localStoragePath = Gdx.files.getLocalStoragePath();
                        Gdx.files.local(toPath).copyTo(Gdx.files.local(append("level/temp/"+levelBean.getLevelId()+"/"+levelBean.getLevelId()+".zip")));
                        reDownLoad(PackZip.unpackZip(
                                append(localStoragePath, "level/temp/"+levelBean.getLevelId()+"/"+levelBean.getLevelId()+".zip"),
                                append(localStoragePath, "level/md5/")));
                        reDownLoad(PackZip.check(append(localStoragePath,"level/md5/"+levelBean.getLevelId())));
                        Gdx.files.local(append("level/md5/"+levelBean.getLevelId()+"/")).copyTo(Gdx.files.local("finallevel/"+levelBean.getVersion()+"/"));
                        runnable.run();
                    }
                }.start();
            }
        };
        failed = new DownLoadListener() {
            @Override
            public void downLoadCallBack() {

            }

            @Override
            public void downLoadCallBack(int code) {
                super.downLoadCallBack(code);
            }
        };
        super.downFile();
    }
}
