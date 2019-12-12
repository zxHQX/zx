package cn.powertime.controller;

import cn.powertime.utils.RSAEncrypt;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zx
 * @version V1.0
 * @Package cn.powertime.controller
 * @date 2019/12/9 14:31
 */
public class TestMain {

    @Autowired


    public static void main(String[] args){
        RSAEncrypt encrypt = new RSAEncrypt();

        String message = "zx123";

        System.out.println(message);

        try {
            System.out.println(message = encrypt.encrypt(message));
            System.out.println(encrypt.decrypt(message));
        } catch (Exception e) {
            e.printStackTrace();
        }





    }

}
