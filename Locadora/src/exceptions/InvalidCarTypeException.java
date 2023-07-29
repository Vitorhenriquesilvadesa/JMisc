package exceptions;

public class InvalidCarTypeException extends RuntimeException {

    public InvalidCarTypeException(String message) {
        super(message);
    }
}
