package cn.powertime.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * @author yyd
 * @version V1.0
 * @Package com.powertime.audiltor.utils
 * @date 2019/11/6 14:13
 */
public class GetCurrentTimeUtil {

    public static Date getCurrentTime(){
        Date date = null;
        String webUrl = "http://www.baidu.com";
        URL url= null;
        try {
            url = new URL(webUrl);

            URLConnection conn = url.openConnection();
            conn.connect();
            long dateL = conn.getDate();
            date = new Date(dateL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return date;
    }
}
