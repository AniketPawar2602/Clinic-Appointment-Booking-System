package com.example.Clinic_Appoitment_App.Repository;

import com.example.Clinic_Appoitment_App.Model.Appointment;
import com.example.Clinic_Appoitment_App.Model.Patient;
import com.example.Clinic_Appoitment_App.Model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctor_DoctorIdAndTimeSlot_SlotIdAndAppointmentDate(Long doctorId, Long slotId, LocalDate appointmentDate);

    List<Appointment> findByDoctor_DoctorIdAndAppointmentDate(Long doctorId, LocalDate appointmentDate);
}
