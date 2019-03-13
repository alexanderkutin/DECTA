
public class MyException extends RuntimeException {

    public MyException() {

    }

    public MyException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }
}
