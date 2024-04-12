package kw.artpuzzle.group.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ArrayMap;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.resource.cocosload.CocosResource;

import kw.artpuzzle.data.GameData;
import kw.artpuzzle.data.SelectItemBean;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/11 19:35
 */
public class SelectDiffItem extends Group {
    private Group rootgroup;
    private Vector2 temp = new Vector2();
    private float offsetX = 200;
    public SelectDiffItem(SelectItemBean selectItemBean){
        setName("selectItemBean"+selectItemBean.getId());
        rootgroup = CocosResource.loadFile("cocos/selectitem.json");
        addActor(rootgroup);
        setSize(rootgroup.getWidth(),rootgroup.getHeight());
        Actor piecemodel = rootgroup.findActor("piecemodel");
        piecemodel.setColor(Color.valueOf("#dee4eb"));
        Label piececoinlabel = rootgroup.findActor("piececoinlabel");
        piececoinlabel.setColor(Color.BLACK);
        piececoinlabel.setText(selectItemBean.getRewardcoin());
        Label piecesnum = rootgroup.findActor("piecesnum");
        piecesnum.setText(selectItemBean.getPiecesnum());
        piecesnum.setColor(Color.valueOf("#7d8493"));
        Actor selectitem = rootgroup.findActor("selectitem");
        selectitem.setOrigin(Align.center);
        selectitem.setScale(0.8f);
        setOrigin(Align.center);
    }


    public void select(){
        Actor piecemodel = rootgroup.findActor("piecemodel");
        piecemodel.setColor(Color.valueOf("#35c75a"));
        Label piecesnum = rootgroup.findActor("piecesnum");
        piecesnum.setColor(Color.valueOf("#7d8493"));
    }

    public void unselect(){
        Actor piecemodel = rootgroup.findActor("piecemodel");
        piecemodel.setColor(Color.valueOf("#dee4eb"));
        Label piecesnum = rootgroup.findActor("piecesnum");
        piecesnum.setColor(Color.WHITE);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        calu();
        if (temp.x > Constant.GAMEWIDTH/2.0f - offsetX && temp.x<Constant.GAMEWIDTH/2.0f + offsetX){
            float v = Math.abs(temp.x - Constant.GAMEWIDTH / 2.0f);
            setScale(1.0f+0.4f*(Math.abs(v - offsetX)/offsetX));
        }else{
            setScale(1.0f);
        }
    }


    public void calu(){
        temp.set(getX(Align.center),getY(Align.center));
        this.getParent().localToStageCoordinates(temp);
    }

    public Vector2 getTemp() {
        return temp;
    }
}
