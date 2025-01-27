package uk.co.payr.payrusersapi.user.exception;

import lombok.Getter;

@Getter
public abstract class AbstractException extends RuntimeException {

    private int httpStatusCode;

    AbstractException(final String message, final int httpStatusCode) {
        super(message);
    }
}
