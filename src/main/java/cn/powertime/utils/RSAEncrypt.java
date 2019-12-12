package cn.powertime.utils;

import org.apache.commons.codec.binary.Base64;

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
 * @Package cn.powertime.utils
 * @date 2019/12/4 11:15
 *
 * 公钥加密私钥解密
 */

public class RSAEncrypt {

        private final String publicKeyKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSzhm+Kh53l+E9C46vl3O1e32+tCzeTObKZsra7pg7XsHJ5sI0RdKmauVon4sDJGOfiD600eLH9gh+6MOewCR9VyFBnstQHXRCWcoRpqh+pM6bRktyC7Tenoxx0PPgsGIxLHuMMqBT9vXPmqPlpHFOFNec9T9hRaozkLk7HzooKwIDAQAB";
        private final String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJLOGb4qHneX4T0Ljq+Xc7V7fb60LN5M5spmytrumDtewcnmwjRF0qZq5WifiwMkY5+IPrTR4sf2CH7ow57AJH1XIUGey1AddEJZyhGmqH6kzptGS3ILtN6ejHHQ8+CwYjEse4wyoFP29c+ao+WkcU4U15z1P2FFqjOQuTsfOigrAgMBAAECgYAl3iYDyIeNX88I2RdYMd/1+0HHVGCSgxGxOeyvpoX8IogoVW7Y3v3kUYSVhTnteJB+ET3jlJRD8jHk0pPLOlLu350g/7P1mKgXZYMJiVjLbLGyKJYYgdyvz+eecPrI7V5zV15EkcxTY9py46rCH3XdY39p8Fpdl7emKsdqI1wm6QJBAPA+giO99OMO4+4a/LfTIxiY41k78O7v/rYoJWsQTRkek7Acks0Ip3J/yRPkpjRJ73BgiBQtSLpc5qMFH69ETCcCQQCcbtSrPUAkbXRieFpE27yIuZrgaXR7vu2eaNYSu51HxTt67FL4JwD4QBLCsK7YWyW+NkawhCRa0vk1hSYKilJdAkEAwx5RfgvqV3shfaHnfLj7eR+7Dh9TuAutOG50rXbI10zxMAiU5fdQX/sO6Zw7rJ4b3I5aFuEMf2eyXdZsfGGg4wJAdJjnSvtMY9pxdTPflJNz4zT0i3AOsm2NDxV0+mF8yZHo06Zx8SShRGf9k7+9kuXdK/molsjnkArQVWP5BsIywQJBAJc81K8YnB5q6Yy30AqKbacG1jXuUCcmzy7HBhJczReqfL/M7gxEW1L8x5yUlvCDgit+uMR0pKOF9gx9IvQLX80=";

        private static Map<Integer, String> keyMap = new HashMap<Integer, String>();  //用于封装随机产生的公钥与私钥
        public void main(String[] args) throws Exception {
            //生成公钥和私钥
            genKeyPair();
            //加密字符串
            String message = "";
            System.out.println("随机生成的公钥为:" + keyMap.get(0));
            System.out.println("随机生成的私钥为:" + keyMap.get(1));
            String messageEn = encrypt(message,keyMap.get(0));
            System.out.println(message + "\t加密后的字符串为:" + messageEn);
            String messageDe = decrypt(messageEn,keyMap.get(1));
            System.out.println("还原后的字符串为:" + messageDe);
        }

        /**
         * 随机生成密钥对
         * @throws NoSuchAlgorithmException
         */
        public void genKeyPair() throws NoSuchAlgorithmException {
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
        }
        /**
         * RSA公钥加密
         *
         * @param str
         *            加密字符串
         * @param publicKey
         *            公钥
         * @return 密文
         * @throws Exception
         *             加密过程中的异常信息
         */
        public String encrypt( String str, String publicKey ) throws Exception{
            //base64编码的公钥
            byte[] decoded = Base64.decodeBase64(publicKey);
            RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
            //RSA加密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
            return outStr;
        }

    /**
     * RSA公钥加密
     *
     * @param str
     *            加密字符串
     * @param publicKey
     *            公钥
     * @return 密文
     * @throws Exception
     *             加密过程中的异常信息
     */
    public String encrypt(String str) throws Exception{
       return encrypt(str,publicKeyKey);
    }

        /**
         * RSA私钥解密
         *
         * @param str
         *            加密字符串
         * @param privateKey
         *            私钥
         * @return 铭文
         * @throws Exception
         *             解密过程中的异常信息
         */
        public String decrypt(String str, String privateKey) throws Exception{
            //64位解码加密后的字符串
            byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
            //base64编码的私钥
            byte[] decoded = Base64.decodeBase64(privateKey);
            RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
            //RSA解密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            String outStr = new String(cipher.doFinal(inputByte));
            return outStr;
        }
    /**
     * RSA私钥解密
     *
     * @param str
     *            加密字符串
     * @param privateKey
     *            私钥
     * @return 铭文
     * @throws Exception
     *             解密过程中的异常信息
     */
    public String decrypt(String str) throws Exception{
       return decrypt(str,privateKey);
    }


}
