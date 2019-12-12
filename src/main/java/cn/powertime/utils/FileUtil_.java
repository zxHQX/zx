package cn.powertime.utils;

import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author zx
 * @version V1.0
 * @Package cn.powertime.evaluation.util
 * @date 2019/11/29 18:10
 */
@Component
public class FileUtil_ {

    public boolean delFile(File file){

        if(file.exists()&&file.isFile()){
            file.delete();
            return true;
        }
        return false;
    }

    public boolean delDiectory(File file){
        if(file.exists()&&file.isDirectory()){
            file.delete();
            return true;
        }
        return false;
    }

}
