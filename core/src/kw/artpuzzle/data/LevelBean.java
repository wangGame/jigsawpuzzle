package kw.artpuzzle.data;

import com.kw.gdx.resource.csvanddata.Value;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/8 12:11
 */
public class LevelBean {
//    game_sort,level_id,level_num,version
    private int game_sort;
    private String level_id;
    private int level_num;
    private String version;

    public int getGame_sort() {
        return game_sort;
    }

    public void setGame_sort(int game_sort) {
        this.game_sort = game_sort;
    }

    public String getLevel_id() {
        return level_id;
    }

    public void setLevel_id(String level_id) {
        this.level_id = level_id;
    }

    public int getLevel_num() {
        return level_num;
    }

    public void setLevel_num(int level_num) {
        this.level_num = level_num;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "LevelBean{" +
                "game_sort=" + game_sort +
                ", level_id='" + level_id + '\'' +
                ", level_num=" + level_num +
                ", version='" + version + '\'' +
                '}';
    }
}
