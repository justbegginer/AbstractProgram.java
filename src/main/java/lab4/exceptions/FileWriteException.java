package lab4.exceptions;

import java.io.IOException;

public class FileWriteException extends IOException {
    public FileWriteException(String message) {
        super(message);
    }
}
