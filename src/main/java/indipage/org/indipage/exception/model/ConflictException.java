package indipage.org.indipage.exception.model;

import indipage.org.indipage.exception.Error;

public class ConflictException extends CustomException {

    public ConflictException(Error error, String message) {
        super(error, message);
    }
}

