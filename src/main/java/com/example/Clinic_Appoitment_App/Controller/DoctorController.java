package com.example.Clinic_Appoitment_App.Controller;

import com.example.Clinic_Appoitment_App.Model.Doctor;
import com.example.Clinic_Appoitment_App.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add-doctor")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor){
        String rawPassword = doctor.getPassword();
        String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());

        doctor.setPassword(hashedPassword);
        return new ResponseEntity<>(doctorService.addDoctor(doctor), HttpStatus.CREATED);
    }

    @GetMapping("/get-doctors")
    public List<Doctor> getDoctors(){
        return doctorService.getDoctors();
    }

    @GetMapping("/get-byId")
    public ResponseEntity<Optional<Doctor>> getDoctorById(@RequestParam Long id){
        return new ResponseEntity<>(doctorService.getDoctorById(id), HttpStatus.FOUND);
    }

    @PutMapping("/update-doctor")
    public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor){
        return new ResponseEntity<>(doctorService.updateDoctor(doctor), HttpStatus.OK);
    }

    @DeleteMapping("/delete-doctor")
    public void deleteDoctor(@RequestParam Long id){
        doctorService.deleteDoctor(id);
    }
}