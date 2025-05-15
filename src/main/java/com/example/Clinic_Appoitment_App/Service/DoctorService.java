package com.example.Clinic_Appoitment_App.Service;

import com.example.Clinic_Appoitment_App.Model.Doctor;
import com.example.Clinic_Appoitment_App.Repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor addDoctor(Doctor doctor) {
        doctor.setPassword(new BCryptPasswordEncoder().encode(doctor.getPassword()));
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor updateDoctor(Doctor doctor) {
        doctor.setPassword(new BCryptPasswordEncoder().encode(doctor.getPassword()));
        return doctorRepository.save(doctor);
    }


    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}