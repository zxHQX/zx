package cn.powertime.utils.json;

import com.alibaba.fastjson.JSONObject;

/**
 *
 * <dependency>
     <groupId>com.alibaba</groupId>
     <artifactId>fastjson</artifactId>
     <version>1.2.61</version>
   </dependency>
 *
 *
 * @author zx
 * @version V1.0
 * @Package cn.powertime.utils.json
 * @date 2019/12/10 18:05
 */
public class FastJsonUtils<T>{

    public static JSONObject jsonStr2JsonObject(String jsonStr){
        return JSONObject.parseObject(jsonStr);
    }

    public T stringStr2JavaBean(String jsonStr){

//        Object parse = JSON.parse(jsonStr, );
        return null;
    }


}
