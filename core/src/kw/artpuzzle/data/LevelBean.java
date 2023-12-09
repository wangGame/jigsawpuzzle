package kw.artpuzzle.data;

import com.kw.gdx.resource.csvanddata.Value;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/8 12:11
 */
public class LevelBean {
    private int sortId;
    private String levelUUID;
    private String levelId;
    @Value("cateGory")
    private String typecateGory;
    private String unlockCost;
    private String version;

    public LevelBean(){
        version = "error";
    }

    public void setLevelUUID(String levelUUID) {
        this.levelUUID = levelUUID;
    }

    public String getLevelUUID() {
        return levelUUID;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getTypecateGory() {
        return typecateGory;
    }

    public void setTypecateGory(String typecateGory) {
        this.typecateGory = typecateGory;
    }

    public String getUnlockCost() {
        return unlockCost;
    }

    public void setUnlockCost(String unlockCost) {
        this.unlockCost = unlockCost;
    }

    @Override
    public String toString() {
        return "LevelBean{" +
                "sortId=" + sortId +
                ", levelId='" + levelId + '\'' +
                ", typecateGory='" + typecateGory + '\'' +
                ", unlockCost='" + unlockCost + '\'' +
                '}';
    }
}
