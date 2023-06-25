package indipage.org.indipage.exception.model;

import indipage.org.indipage.exception.Error;

public class UnauthorizedException extends CustomException {

    public UnauthorizedException(Error error, String message) {
        super(error, message);
    }
}

