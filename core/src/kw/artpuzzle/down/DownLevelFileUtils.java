package kw.artpuzzle.down;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.files.FileHandle;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.net.DownLoadListener;
import com.kw.gdx.zip.PackZip;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import kw.artpuzzle.constant.GameStaticInstance;
import kw.artpuzzle.data.LevelBean;
import kw.artpuzzle.pref.JigsawPreference;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/8 15:56
 *
 *
 * 下载csv
 */
public class DownLevelFileUtils {
    private boolean alreadyUpdate;
    public void check(){
        Net.HttpRequest httpRequest = new Net.HttpRequest("POST");
        httpRequest.setUrl(NetContant.levelConfigUrl);
        httpRequest.setTimeOut(3000);
        JSONObject requestParam = new JSONObject().put("method", "normal_config");
        httpRequest.setContent(requestParam.toString());
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String resultAsString = httpResponse.getResultAsString();
                JSONObject result = new JSONObject(resultAsString);
                String status = result.getString("status");
                if (status.equals("success")) {
                    String levelOrder = result.getString("levelOrder");
                    String levelPreOrder = result.getString("normalLevelPreOrder");
                    Constant.severLevelOrder = levelOrder;
                    Constant.severLevelPreOrder = levelPreOrder;
                    if (levelOrder!=null&&!levelOrder.equals("")) {
                        if (!Gdx.files.internal("csv/level/" + levelOrder + ".csv").exists()){
                            if (!Gdx.files.local("csv/level/" + levelOrder + ".csv").exists()) {
                                LevelOrderDownload levelOrderDownload = new LevelOrderDownload(levelOrder+".csv", new Runnable() {
                                    @Override
                                    public void run() {
                                        Gdx.app.postRunnable(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    FileHandle local = Gdx.files.local("csv/temp/" + levelOrder+".csv");
                                                    FileHandle local1 = Gdx.files.local("csv/level/"+ levelOrder+".csv");
                                                    local.moveTo(local1);
                                                    JigsawPreference.getInstance().saveDownloadLevelTemp(levelOrder);
                                                    if (Constant.severLevelOrder!=null && Constant.severLevelPreOrder!=null){
                                                        String levelOrder1 = JigsawPreference.getInstance().getDownloadLevelTemp();
                                                        String levelPreOrder1 = JigsawPreference.getInstance().getDownloadLevelPreTemp();
                                                        if (Constant.severLevelOrder.equals(levelOrder1)
                                                                && Constant.severLevelPreOrder.equals(levelPreOrder1)){
                                                            if (!alreadyUpdate) {
                                                                JigsawPreference.getInstance().saveDownloadLevelPre(levelPreOrder1);
                                                                JigsawPreference.getInstance().saveDownloadLevel(levelOrder1);
                                                                alreadyUpdate = true;
                                                            }
                                                        }
                                                    }
                                                }catch (Exception e){
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                    }
                                });

                                levelOrderDownload.downLoad();
                            }
                        }
                    }

                    if (!levelPreOrder.equals("")) {
                        if (!Gdx.files.internal("csv/levelpre/" + levelPreOrder+".csv").exists()) {
                            if (!Gdx.files.local("csv/levelpre/" + levelPreOrder + ".csv").exists()) {
                                LevelOrderDownload levelOrderDownload = new LevelOrderDownload(
                                        levelPreOrder + ".csv", new Runnable() {
                                    @Override
                                    public void run() {
                                        Gdx.app.postRunnable(new Runnable() {
                                            @Override
                                            public void run() {
                                                Gdx.app.postRunnable(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        try {
                                                            FileHandle local = Gdx.files.local("csv/temp/" + levelPreOrder + ".csv");
                                                            FileHandle local1 = Gdx.files.local("csv/levelpre/" + levelPreOrder + ".csv");
                                                            local.moveTo(local1);
                                                            JigsawPreference.getInstance().saveDownloadLevelPreTemp(levelPreOrder);
                                                            if (Constant.severLevelOrder != null && Constant.severLevelPreOrder != null) {
                                                                String levelOrder1 = JigsawPreference.getInstance().getDownloadLevelTemp();
                                                                String levelPreOrder1 = JigsawPreference.getInstance().getDownloadLevelPreTemp();
                                                                if (Constant.severLevelOrder.equals(levelOrder1)
                                                                        && Constant.severLevelPreOrder.equals(levelPreOrder1)) {
                                                                    if (!alreadyUpdate) {
                                                                        JigsawPreference.getInstance().saveDownloadLevelPre(levelPreOrder1);
                                                                        JigsawPreference.getInstance().saveDownloadLevel(levelOrder1);
                                                                        alreadyUpdate = true;
                                                                    }
                                                                }
                                                            }
                                                        } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                                levelOrderDownload.downLoad();
                            }
                        }
                    }
                }
            }

            @Override
            public void failed(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void cancelled() {

            }
        });
    }
}
