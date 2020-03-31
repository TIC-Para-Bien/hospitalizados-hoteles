package org.ticparabien.hotelcovid19.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class RoomMaxCapacityReached extends RuntimeException {

    public RoomMaxCapacityReached(String message) {
        super(message);
    }
}
