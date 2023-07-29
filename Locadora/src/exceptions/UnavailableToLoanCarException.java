package exceptions;

public class UnavailableToLoanCarException extends RuntimeException {

    public UnavailableToLoanCarException(String message) {
        super(message);
    }
}
