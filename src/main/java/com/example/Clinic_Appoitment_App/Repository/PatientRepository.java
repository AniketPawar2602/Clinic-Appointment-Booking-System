package com.example.Clinic_Appoitment_App.Repository;

import com.example.Clinic_Appoitment_App.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
