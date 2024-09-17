package kw.artpuzzle.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.constant.Constant;

import kw.artpuzzle.constant.GameConstant;
import kw.artpuzzle.view.ModelGroup;
import kw.artpuzzle.view.TempView;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/4 10:42
 */
public class GameLogicUtils {
    private TempView view;
    private Vector2 targetPos;
    public GameLogicUtils(TempView view){
        this.view = view;
        targetPos = new Vector2();
    }

    public Vector2 findTargetPos(String name){
        Actor actor = view.findActor(""+name);
        targetPos.set(actor.getX(Align.center) ,actor.getY(Align.center));
        return targetPos;
    }

    public boolean check(Vector2 targetPos,Vector2 vector2){
        Vector2 vector21 = new Vector2(vector2);
        Vector2 sub = vector21.sub(targetPos);
        float v = sub.x * sub.x + sub.y * sub.y;

        if (v<10000.0f/(GameConstant.modelScale* GameConstant.modelScale)){
            return true;
        }else {
            return false;
        }
    }

    public void convertTarget(Vector2 vector2) {
        view.stageToLocalCoordinates(vector2);
    }

    public void addActor(ModelGroup group) {
        view.addFindActor(group);
    }

    public boolean checkValue(int maskIndex) {

        int arr[] = new int[]{
                -1,1,GameConstant.rowNum,-GameConstant.rowNum
        };
//        int i = current - 1;
//        int i1 = current + 1;
//        int i2 = current + allNumer;
//        int i3 = current - allNumer;
        int hang = maskIndex / GameConstant.rowNum;
        int line = maskIndex % GameConstant.rowNum;
        //边缘
        if (hang == 0 || hang == GameConstant.rowNum - 1){
            return true;
        }
        if (line == 0||line == GameConstant.rowNum - 1){
            return true;
        }
        for (int i : arr) {
            int i1 = maskIndex + i;
            Actor modelGroup = view.findActor("final" + i1);
            if (modelGroup!=null){
                return true;
            }
        }
        return false;
    }
}
