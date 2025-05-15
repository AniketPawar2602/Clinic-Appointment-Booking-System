package com.example.Clinic_Appoitment_App.Service;

import com.example.Clinic_Appoitment_App.Model.Patient;
import com.example.Clinic_Appoitment_App.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient addPatient(Patient patient) {
        patient.setPassword(new BCryptPasswordEncoder().encode(patient.getPassword()));
        return patientRepository.save(patient);
    }

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id){
        return patientRepository.findById(id);
    }


    public Patient updatePatient(Patient patient) {
        patient.setPassword(new BCryptPasswordEncoder().encode(patient.getPassword()));
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
