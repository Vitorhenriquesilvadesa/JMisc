package exceptions;

public class PermissionToLoanDeniedException extends RuntimeException {

    public PermissionToLoanDeniedException(String message) {
        super(message);
    }
}