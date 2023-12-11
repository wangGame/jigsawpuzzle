package com.kw.gdx.file;

import com.badlogic.gdx.utils.ArrayMap;

/**
 * @Auther jian xian si qi
 * @Date 2023/12/11 17:03
 */
public class BaseFile {
    private String fileName;
    public BaseFile(String fileName){
        this.fileName = fileName;
    }

    protected String getFileName() {
        return fileName;
    }

}
