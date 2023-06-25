package indipage.org.indipage.exception.model;

import indipage.org.indipage.exception.Error;

public class BadRequestException extends CustomException {

    public BadRequestException(Error error, String message) {
        super(error, message);
    }
}
