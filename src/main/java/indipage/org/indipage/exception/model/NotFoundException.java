package indipage.org.indipage.exception.model;

import indipage.org.indipage.exception.Error;

public class NotFoundException extends CustomException {

    public NotFoundException(Error error, String message) {
        super(error, message);
    }
}