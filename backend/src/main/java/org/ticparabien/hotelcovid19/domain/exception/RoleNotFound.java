package org.ticparabien.hotelcovid19.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RoleNotFound extends RuntimeException {

    public RoleNotFound(String message) {
        super(message);
    }
}
