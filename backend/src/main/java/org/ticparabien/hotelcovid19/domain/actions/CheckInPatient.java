package org.ticparabien.hotelcovid19.domain.actions;

import org.springframework.stereotype.Component;
import org.ticparabien.hotelcovid19.domain.Patient;
import org.ticparabien.hotelcovid19.domain.Room;
import org.ticparabien.hotelcovid19.domain.exception.PatientNotFound;
import org.ticparabien.hotelcovid19.domain.exception.RoomNotFound;
import org.ticparabien.hotelcovid19.domain.repositories.PatientRepository;
import org.ticparabien.hotelcovid19.domain.repositories.RoomRepository;

@Component
public class CheckInPatient {

    private final PatientRepository patientRepository;

    private final RoomRepository roomRepository;

    public CheckInPatient(PatientRepository patientRepository, RoomRepository roomRepository) {
        this.patientRepository = patientRepository;
        this.roomRepository = roomRepository;
    }

    public void execute(Integer patientId, Integer roomId) {
        // TODO Check max room capacity not exceeded
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFound("Room with ID " + roomId + " not found."));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFound("Patient with ID " + patientId + " not found."));
        patient.setRoom(room); // Notice this does a patient check out from its current room automatically
        patientRepository.save(patient);
    }
}
