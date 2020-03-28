package org.ticparabien.hotelcovid19.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientNotFound extends RuntimeException {

    public PatientNotFound(String message) {
        super(message);
    }
}
