package lab4.exceptions;

import java.io.IOException;

public class FileReadException extends IOException {
    public FileReadException(String message){
        super(message);
    }
}
