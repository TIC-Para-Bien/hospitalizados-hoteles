package org.ticparabien.hotelcovid19.domain;

public class LastReportedHealthRecord {
    private float fever;

    public LastReportedHealthRecord(float fever) {
        this.fever = fever;
    }

    public float getFever() {
        return fever;
    }
}
