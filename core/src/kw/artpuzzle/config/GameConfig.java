package kw.artpuzzle.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.utils.log.NLog;

import kw.artpuzzle.constant.LevelConfig;
import kw.artpuzzle.down.NetContant;

public class GameConfig {
    public GameConfig(){
        Gdx.isJiami = true;
        NLog.isLog = true;
        Constant.viewColor.set(Color.WHITE);
        Asset.enterGameClear();
        NetContant.levelConfigUrl = LevelConfig.monthConfigUrl;
    }
}
