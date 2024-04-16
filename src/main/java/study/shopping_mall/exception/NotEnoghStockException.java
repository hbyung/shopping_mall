package study.shopping_mall.exception;

public class NotEnoghStockException extends RuntimeException {

    //override = ctrl + o

    public NotEnoghStockException() {
        super();
    }

    public NotEnoghStockException(String message) {
        super(message);
    }

    public NotEnoghStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoghStockException(Throwable cause) {
        super(cause);
    }

    protected NotEnoghStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
