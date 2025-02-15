package mk.ukim.finki.db.distributorapp._exceptions;

public class PasswordsDoNotMatchException extends RuntimeException {
    public PasswordsDoNotMatchException() {
        super("The two passwords do not match.");
    }
}
