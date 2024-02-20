package kw.artpuzzle.pref;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/11 16:37
 */
public class JigsawPreference {
    private Preferences preferences;

    public String getTheme() {
        return preferences.getString("theme","0.png");
    }

    public void saveTheme(String name){
        preferences.putString("theme",name);
        preferences.flush();
    }

    public void saveDownloadLevelTemp(String levelOrder) {
        preferences.putString("levelOrderTemp",levelOrder);
        preferences.flush();
    }

    public void saveDownloadLevelPreTemp(String levelOrder) {
        preferences.putString("levelPreOrderTemp",levelOrder);
        preferences.flush();
    }

    public String getDownloadLevelTemp() {
        return preferences.getString("levelOrderTemp","");
    }

    public String getDownloadLevelPreTemp() {
        return preferences.getString("levelPreOrderTemp","");
    }

    public void saveDownloadLevelPre(String levelPreOrder1) {
        preferences.putString("levelPreOrder",levelPreOrder1);
        preferences.flush();
    }

    public void saveDownloadLevel(String levelOrder1) {
        preferences.putString("levelOrder",levelOrder1);
        preferences.flush();
    }

    private static class instance{
        private static final JigsawPreference jigsawPreference = new JigsawPreference();
    }
    private JigsawPreference(){
        preferences = Gdx.app.getPreferences("jigsawpuzzle");
    }

    public static JigsawPreference getInstance(){
        return instance.jigsawPreference;
    }

    public void saveCoinNum(int num){
        preferences.putInteger("jigsawcoin",num + getCoinNum());
    }

    public int getCoinNum(){
        return preferences.getInteger("jigsawcoin",0);
    }
}
