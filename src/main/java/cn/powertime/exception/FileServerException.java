package cn.powertime.exception;

public class FileServerException extends RuntimeException {
    public FileServerException() {
    }

    public FileServerException(String message) {
        super(message);
    }

    public FileServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileServerException(Throwable cause) {
        super(cause);
    }
}
