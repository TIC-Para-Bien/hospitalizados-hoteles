package org.ticparabien.hotelcovid19.domain;

public class Patient {
    public Integer id;
    public String personalId;
    public String phone;
    public String name;

    public Patient(Integer id, String personalId, String phone, String name) {
        this.id = id;
        this.personalId = personalId;
        this.phone = phone;
        this.name = name;
    }
}
