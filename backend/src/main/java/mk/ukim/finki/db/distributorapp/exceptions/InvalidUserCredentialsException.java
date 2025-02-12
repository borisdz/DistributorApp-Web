package mk.ukim.finki.db.distributorapp.exceptions;

public class InvalidUserCredentialsException extends RuntimeException {

    public InvalidUserCredentialsException() {
        super("Invalid user credentials");
    }
}
