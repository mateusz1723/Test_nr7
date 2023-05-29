package pl.kurs.shape_api.exceptionhandling;

public class NotUniqueUsernameException extends Exception{
    public NotUniqueUsernameException(String message) {
        super(message);
    }
}
