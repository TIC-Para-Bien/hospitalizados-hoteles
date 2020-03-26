package org.ticparabien.hotelcovid19.domain;

public class Patient {
    private Integer id;
    private String personalId;
    private String phone;
    private String name;
    private LastReportedHealthRecord lastReportedHealthRecord;

    public Patient(Integer id, String personalId, String phone, String name) {
        this.id = id;
        this.personalId = personalId;
        this.phone = phone;
        this.name = name;
    }

    public float temperature() {
        return lastReportedHealthRecord.getFever();
    }

    public Integer getId() {
        return id;
    }

    public void setLastReportedHealthRecord(LastReportedHealthRecord lastReportedHealthRecord) {
        this.lastReportedHealthRecord = lastReportedHealthRecord;
    }
}
