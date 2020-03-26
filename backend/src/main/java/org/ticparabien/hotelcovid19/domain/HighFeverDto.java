package org.ticparabien.hotelcovid19.domain;

public class HighFeverDto {
    public Integer patientId;
    public float temperature;

    public HighFeverDto(Integer patientId, float temperature) {
        this.patientId = patientId;
        this.temperature = temperature;
    }
}
