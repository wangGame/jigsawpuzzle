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
    private static GameData instance;

    public GameData(){
        levelSortData = new ArrayMap<>();
    }

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
        Array<LevelBean> common = CsvUtils.common("csv/levelpre/levelPreOrderB.csv", LevelBean.class, true);
        for (int i = 0; i < common.size; i++) {
            LevelBean levelBean = common.get(i);
            levelSortData.put(levelBean.getGame_sort(),levelBean);
        }
    }

    public ArrayMap<Integer,LevelBean> readyDaily(String yearAndMonth){
        ArrayMap<Integer,LevelBean> levelBeanArrayMap = new ArrayMap<>();
        try {
            Array<LevelBean> common = CsvUtils.common("daily/"+yearAndMonth, LevelBean.class, true);
            for (int i = 1; i <= common.size; i++) {
                LevelBean levelBean = common.get(i-1);
                levelBeanArrayMap.put(i,levelBean);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return levelBeanArrayMap;
    }

    public ArrayMap<Integer, CollectionBean> readCollectionCateDetail(String name) {
        ArrayMap<Integer,CollectionBean> cateDetailMap = new ArrayMap<>();
        cateDetailMap.clear();
        Array<CollectionBean> common = CsvUtils.common(
                "category/categorydetail/"+name+".csv",
                CollectionBean.class);
        for (int i = 0; i < common.size; i++) {
            CollectionBean collectionBean = common.get(i);
            cateDetailMap.put(collectionBean.getSortId(),collectionBean);
        }
        return cateDetailMap;
    }
}
