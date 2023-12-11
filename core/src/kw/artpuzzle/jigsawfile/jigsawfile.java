package kw.artpuzzle.jigsawfile;

import com.badlogic.gdx.utils.Array;
import com.kw.gdx.file.BaseFile;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/11 17:02
 */
public class jigsawfile extends BaseFile {

    public jigsawfile() {
        super("jigsawhistoryfile.txt");
    }

    public void saveHistoryPic(){
        Array<String> history = new Array<>();

    }

}
