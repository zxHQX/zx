package cn.powertime.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author zx
 * @version V1.0
 * @Package com.powertime.audiltor.utils
 * @date 2019/11/12 11:56
 */
public class PropertiesUtils_<T> {


    //传入类相对路径，返回properties
    public Properties getProperties(String classpath){
        Properties prop = new Properties();
        String value = null;
        try {

            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config/logTimeToLiveConfig.properties");
            prop.load(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return prop;
    }

}
