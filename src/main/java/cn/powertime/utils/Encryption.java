package cn.powertime.utils;

import cn.powertime.exception.MyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author zx
 * @version V1.0
 * @Package cn.powertime.utils
 * @date 2019/12/4 10:48
 */
public class Encryption {


    /**
     * 签名排序一( ASCII 码从小到大排序（字典序）)
     *
     * @param map
     * @return
     */
    public static String sortMapKey(Map<String, String> map) {
        //将map放入到List中
        List<Map.Entry<String, String>> infoIds = new ArrayList<>(map.entrySet());
        //排序
        infoIds.sort(Comparator.comparing(o -> (o.getKey())));
        // 构造URL 键值对的格式
        StringBuilder buf = new StringBuilder();
        //循环构建键值对字符串
        for (Map.Entry<String, String> item : infoIds) {
            if (StringUtils.isNotBlank(item.getKey())) {
                String key = item.getKey();
                String val = item.getValue();
                buf.append(key).append("=").append(val);
                buf.append("&");
            }
        }
        String buff = buf.toString();
        if (!buff.isEmpty()) {
            buff = buff.substring(0, buff.length() - 1);
        }
        return buff;
    }

    /**
     * 签名排序二（字母顺序）
     * @param requestParam
     * @return
     */
    public static String formPublicParam(Map<String, String> requestParam) {
        //所有的参数，这里使用TreeMap， 好处在于天然有序，默认是字母顺序
        Map<String, String> params = new TreeMap<>();
        params.putAll(requestParam);
        //访问的URL
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, String> param : params.entrySet()) {
            buffer.append(param.getKey() + "=" + param.getValue());
        }
        return buffer.toString();
    }



    /**
     * MD5签名
     *
     * @param map
     * @return
     */
    public static String sign(Map<String, String> map) {
        //排序
        String params = sortMapKey(map);
        //调用md5签名
        return DigestUtils.md5DigestAsHex(params.getBytes());
    }

    /**
     * 验证签名
     *
     * @param map
     * @param test
     * @return
     */
    public static boolean verification(Map<String, String> map, String test) {
        String sign = sign(map);
        return sign.equals(test);
    }


    /**
     * SHA1安全加密算法   
     *
     * @parammaps参数key-valuemap集合
     * @return 
     * @throwsDigestException
     */
    public static String shaSign(Map<String, String> maps) throws DigestException {
        //获取信息摘要-参数排序后字符串
        String decrypt = sortMapKey(maps);
        try {
            //指定sha1算法    
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            //Updates the digest using the specified array of bytes.
            digest.update(decrypt.getBytes());
            //获取字节数组      
            byte messageDigest[] = digest.digest();
            //CreateHexString       
            StringBuffer hexString = new StringBuffer();
            //字节数组转换为十六进制数       
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new DigestException("签名错误！");
        }

    }


    /**
     * base64加密
     *
     * @param params
     * @return
     */
    private static String base64Encode(Map<String, String> params) {
        String key = sortMapKey(params);
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(key.getBytes());
        return encode;
    }

    /**
     * base64解密
     *
     * @param encode
     * @return
     */
    private static String base64Decoder(String encode) {
        // 解密
        BASE64Decoder decoder = new BASE64Decoder();
        String decode = null;
        try {
            decode = new String(decoder.decodeBuffer(encode));
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyException(e.getMessage());
        }
        return encode;
    }

    public static final String KEY_MAC = "HmacMD5";


    /**
     * HmacMD5算法签名
     */
    public static String getHmacMD5Sign(Map<String, String> requestParam, String key) {
        String data = sortMapKey(requestParam);
        byte[] inputData = data.getBytes();
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), KEY_MAC);
        Mac mac;
        try {
            mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            return byteArrayToHexString(mac.doFinal(inputData));
        } catch (Exception e) {
            throw new MyException("HmacMD5算法加密失败" + e.getMessage());
        }
    }

    /**
     * byte数组转换为String
     * @param b
     * @return
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }


}
