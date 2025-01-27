package uk.co.payr.payrusersapi.user.exception;

public class UserApiException extends AbstractException {

    public UserApiException(String message, int httpExceptionCode) {
        super(message, httpExceptionCode);
    }
}
