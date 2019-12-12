package cn.powertime.exception;

/**
 * @author yyd
 * @version V1.0
 * @Package cn.powertime.exception
 * @date 2019/12/4 10:51
 */
public class MyException extends RuntimeException {
    public MyException() {
    }
    public MyException(String s) {
        super(s);
    }
}
