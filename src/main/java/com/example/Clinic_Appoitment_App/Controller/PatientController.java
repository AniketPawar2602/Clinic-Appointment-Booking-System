package com.example.Clinic_Appoitment_App.Controller;

import com.example.Clinic_Appoitment_App.Model.Patient;
import com.example.Clinic_Appoitment_App.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/add-patient")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        String rawPassword = patient.getPassword();
        String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());

        patient.setPassword(hashedPassword);
        return new ResponseEntity<>(patientService.addPatient(patient), HttpStatus.CREATED);
    }

    @GetMapping("/get-patients")
    public List<Patient> getPatients(){
        return patientService.getPatients();
    }

    @GetMapping("/get-byId")
    public ResponseEntity<Optional<Patient>> getPatientById(@RequestParam Long id){
        return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.FOUND);
    }

    @PutMapping("/update-patient")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient){
        return new ResponseEntity<>(patientService.updatePatient(patient), HttpStatus.OK);
    }

    @DeleteMapping("/delete-patient")
    public void deletePatient(@RequestParam Long id){
        patientService.deletePatient(id);
    }
}
