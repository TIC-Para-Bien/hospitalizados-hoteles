package org.ticparabien.hotelcovid19.domain;

public class LastReportedHealthRecord {
    private float temperature;

    public LastReportedHealthRecord(float temperature) {
        this.temperature = temperature;
    }

    public float getTemperature() {
        return temperature;
    }
}
