package src.niccolaibalica.Dict;

import src.commons.MovidaFileException;

public class ExceptionKeyNotFound extends RuntimeException {

    public String getMessage() {
        return "Key not found";
    }
}
