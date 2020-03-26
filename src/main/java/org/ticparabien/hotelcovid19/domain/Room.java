package org.ticparabien.hotelcovid19.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Room {

    private String name;
    private Set<Patient> owners;

    public Room(String name, Patient... owners) {
        this.name = name;
        this.owners = new HashSet<>(Arrays.asList(owners));
    }

    public String name() {
        return name;
    }

    public Set<Patient> owners() {
        return new HashSet<>(owners);
    }
}
