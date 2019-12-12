package cn.powertime.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zx
 * @version V1.0
 * @Package com.example.temp
 * @date 2019/12/4 15:08
 *
 * 私钥加密公钥解密
 */
public class RSAEncrypt22 {
    private static Map<Integer, String> keyMap = new HashMap<Integer, String>();  //用于封装随机产生的公钥与私钥


    public static Map<Integer,String> genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        keyMap.put(0,publicKeyString);  //0表示公钥
        keyMap.put(1,privateKeyString);  //1表示私钥
        return keyMap;
    }


    /**
     * 简易版加密解密   。。。随便看看就行
     * @param str
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String decr( String str, String publicKey ) throws Exception{

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

        String msg = "admin";
        System.out.println(msg);

        keyGen.initialize(1024);
        KeyPair key = keyGen.generateKeyPair();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        cipher.init(Cipher.ENCRYPT_MODE, key.getPrivate());
        byte[] cipherText = cipher.doFinal(msg.getBytes("UTF8"));
        System.out.println(new String(cipherText, "UTF8"));

        cipher.init(Cipher.DECRYPT_MODE, key.getPublic());
        byte[] newPlainText = cipher.doFinal(cipherText);
        System.out.println(new String(newPlainText, "UTF8"));
        return "";
    }



    public static String encrypt( String str, String privateKey ) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));

        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, priKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

//    public static String decrypt(String str, String publicKey) throws Exception{
//        //64位解码加密后的字符串
//        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
//        //base64编码的私钥
//        byte[] decoded = Base64.decodeBase64(publicKey);
//        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
//        //RSA解密
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.DECRYPT_MODE, pubKey);
//        String outStr = new String(cipher.doFinal(inputByte));
//        return outStr;
//    }


    public static String decrypt(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = org.apache.commons.codec.binary.Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }


    /**
     * 分段加密
     * size： 分段长度
     */
    public String encryptBigString(String bigString){

        int size = 50;
        //获取私钥
        String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJLOGb4qHneX4T0Ljq+Xc7V7fb60LN5M5spmytrumDtewcnmwjRF0qZq5WifiwMkY5+IPrTR4sf2CH7ow57AJH1XIUGey1AddEJZyhGmqH6kzptGS3ILtN6ejHHQ8+CwYjEse4wyoFP29c+ao+WkcU4U15z1P2FFqjOQuTsfOigrAgMBAAECgYAl3iYDyIeNX88I2RdYMd/1+0HHVGCSgxGxOeyvpoX8IogoVW7Y3v3kUYSVhTnteJB+ET3jlJRD8jHk0pPLOlLu350g/7P1mKgXZYMJiVjLbLGyKJYYgdyvz+eecPrI7V5zV15EkcxTY9py46rCH3XdY39p8Fpdl7emKsdqI1wm6QJBAPA+giO99OMO4+4a/LfTIxiY41k78O7v/rYoJWsQTRkek7Acks0Ip3J/yRPkpjRJ73BgiBQtSLpc5qMFH69ETCcCQQCcbtSrPUAkbXRieFpE27yIuZrgaXR7vu2eaNYSu51HxTt67FL4JwD4QBLCsK7YWyW+NkawhCRa0vk1hSYKilJdAkEAwx5RfgvqV3shfaHnfLj7eR+7Dh9TuAutOG50rXbI10zxMAiU5fdQX/sO6Zw7rJ4b3I5aFuEMf2eyXdZsfGGg4wJAdJjnSvtMY9pxdTPflJNz4zT0i3AOsm2NDxV0+mF8yZHo06Zx8SShRGf9k7+9kuXdK/molsjnkArQVWP5BsIywQJBAJc81K8YnB5q6Yy30AqKbacG1jXuUCcmzy7HBhJczReqfL/M7gxEW1L8x5yUlvCDgit+uMR0pKOF9gx9IvQLX80=";

        StringBuilder sb = new StringBuilder();

        int startIndex = 0;
        int endIndex =size;
        if(bigString.length()<=size){
            endIndex = bigString.length();
        }

        while (true){

            String s = bigString.substring(startIndex,endIndex);
            startIndex=endIndex;

            try {
                s = encrypt(s,privateKey);
            } catch (Exception e) {
                e.printStackTrace();
            }

            sb.append(s);
            if(endIndex<bigString.length()-size){
                endIndex+=size;
            }else{
                endIndex = bigString.length();
            }
            if(startIndex==bigString.length()){
                break;
            }
        }
        return sb.toString();
    }

    /**
     * 分段解密
     * size： 分段长度
     */
    public String decryptBigString(String bigString){

        final int size = 172;
        //获取私钥
        String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJLOGb4qHneX4T0Ljq+Xc7V7fb60LN5M5spmytrumDtewcnmwjRF0qZq5WifiwMkY5+IPrTR4sf2CH7ow57AJH1XIUGey1AddEJZyhGmqH6kzptGS3ILtN6ejHHQ8+CwYjEse4wyoFP29c+ao+WkcU4U15z1P2FFqjOQuTsfOigrAgMBAAECgYAl3iYDyIeNX88I2RdYMd/1+0HHVGCSgxGxOeyvpoX8IogoVW7Y3v3kUYSVhTnteJB+ET3jlJRD8jHk0pPLOlLu350g/7P1mKgXZYMJiVjLbLGyKJYYgdyvz+eecPrI7V5zV15EkcxTY9py46rCH3XdY39p8Fpdl7emKsdqI1wm6QJBAPA+giO99OMO4+4a/LfTIxiY41k78O7v/rYoJWsQTRkek7Acks0Ip3J/yRPkpjRJ73BgiBQtSLpc5qMFH69ETCcCQQCcbtSrPUAkbXRieFpE27yIuZrgaXR7vu2eaNYSu51HxTt67FL4JwD4QBLCsK7YWyW+NkawhCRa0vk1hSYKilJdAkEAwx5RfgvqV3shfaHnfLj7eR+7Dh9TuAutOG50rXbI10zxMAiU5fdQX/sO6Zw7rJ4b3I5aFuEMf2eyXdZsfGGg4wJAdJjnSvtMY9pxdTPflJNz4zT0i3AOsm2NDxV0+mF8yZHo06Zx8SShRGf9k7+9kuXdK/molsjnkArQVWP5BsIywQJBAJc81K8YnB5q6Yy30AqKbacG1jXuUCcmzy7HBhJczReqfL/M7gxEW1L8x5yUlvCDgit+uMR0pKOF9gx9IvQLX80=";

        StringBuilder sb = new StringBuilder();

        int startIndex = 0;
        int endIndex =size;

        while (true){

            String s = bigString.substring(startIndex,endIndex);
            startIndex=endIndex;

            try {
                s = decrypt(s,privateKey);
            } catch (Exception e) {
                e.printStackTrace();
            }

            sb.append(s);
            if(endIndex==bigString.length()){
                break;
            }else if(endIndex<bigString.length()){
                endIndex = endIndex+size;
            }else{
                throw new RuntimeException("传入字符长度有误");
            }
        }
        return sb.toString();
    }

}
