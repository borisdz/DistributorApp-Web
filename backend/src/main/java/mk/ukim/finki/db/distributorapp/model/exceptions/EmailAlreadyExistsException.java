package mk.ukim.finki.db.distributorapp.model.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("User with the email " + email + " already exists.");
    }
}