package kw.artpuzzle.data;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.kw.gdx.resource.csvanddata.demo.CsvUtils;

import java.util.HashSet;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/8 12:11
 */
public class GameData {
    public ArrayMap<Integer,LevelBean> levelSortData;
    public ArrayMap<String,LevelBean> levelUUIDMap;


    public GameData(){
        levelSortData = new ArrayMap<>();
        levelUUIDMap = new ArrayMap<>();
        cateDetailMap = new ArrayMap<>();
    }

    private static GameData instance;

    public static GameData getInstance() {
        if (instance == null) {
            instance = new GameData();
        }
        return instance;
    }

    public ArrayMap<Integer, LevelBean> getLevelSortData() {
        return levelSortData;
    }

    public void loadLevel(){
        levelSortData = new ArrayMap<>();
        Array<LevelBean> common = CsvUtils.common("nomal/nomallevel.csv", LevelBean.class, true);
        HashSet<String> hashSet = new HashSet<>();
        for (int i = 0; i < common.size; i++) {
            LevelBean levelBean = common.get(i);
            levelSortData.put(levelBean.getSortId(),levelBean);
            hashSet.add(levelBean.getTypecateGory());
        }
    }

    public ArrayMap<Integer,LevelBean> readyDaily(String yearAndMonth){
        ArrayMap<Integer,LevelBean> levelBeanArrayMap = new ArrayMap<>();
        Array<LevelBean> common = CsvUtils.common("daily/"+yearAndMonth, LevelBean.class, true);
        for (int i = 1; i <= common.size; i++) {
            LevelBean levelBean = common.get(i-1);
            levelBeanArrayMap.put(i,levelBean);
        }
        return levelBeanArrayMap;
    }

    private ArrayMap<String,CateBean> cateArrayMap;
    public ArrayMap<String,CateBean> readCate() {
        if (cateArrayMap == null){
            cateArrayMap = new ArrayMap<>();
        }
        Array<CateBean> common = CsvUtils.common("category/category.csv", CateBean.class);
        for (int i = 0; i < common.size; i++) {
            CateBean cateBean = common.get(i);
            cateArrayMap.put(cateBean.getDesc(),cateBean);
        }
        return cateArrayMap;
    }


    ArrayMap<Integer,LevelBean> cateDetailMap;
    public ArrayMap<Integer,LevelBean> readCateDetail(String name){
        Array<LevelBean> common = CsvUtils.common("category/categorydetail/"+name+".csv", LevelBean.class);
        for (int i = 0; i < common.size; i++) {
            LevelBean levelBean = common.get(i);
            cateDetailMap.put(levelBean.getSortId(),levelBean);
        }
        return cateDetailMap;
    }
}
