package mk.ukim.finki.db.distributorapp.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String username) {
        super("User with the username "+username+" already exists.");
    }
}
