package org.ticparabien.hotelcovid19.domain;

public class Patient {

    private final String roomNumber;
    private final String phoneNumber;

    public Patient(String roomNumber, String phoneNumber) {
        this.roomNumber = roomNumber;
        this.phoneNumber = phoneNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
