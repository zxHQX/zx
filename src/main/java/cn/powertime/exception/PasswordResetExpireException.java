package cn.powertime.exception;

public class PasswordResetExpireException extends RuntimeException {

    public PasswordResetExpireException() {
    }

    public PasswordResetExpireException(String message) {
        super(message);
    }

    public PasswordResetExpireException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordResetExpireException(Throwable cause) {
        super(cause);
    }
}