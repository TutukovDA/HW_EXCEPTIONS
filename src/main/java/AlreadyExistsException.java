import java.io.IOException;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {

        super(message);
    }
}
