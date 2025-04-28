package com.example.Clinic_Appoitment_App.Service;

import com.example.Clinic_Appoitment_App.DTO.AppointmentRequestDTO;
import com.example.Clinic_Appoitment_App.DTO.AppointmentResponseDTO;
import com.example.Clinic_Appoitment_App.Model.Appointment;
import com.example.Clinic_Appoitment_App.Model.Doctor;
import com.example.Clinic_Appoitment_App.Model.Patient;
import com.example.Clinic_Appoitment_App.Model.TimeSlot;
import com.example.Clinic_Appoitment_App.Repository.AppointmentRepository;
import com.example.Clinic_Appoitment_App.Repository.DoctorRepository;
import com.example.Clinic_Appoitment_App.Repository.PatientRepository;
import com.example.Clinic_Appoitment_App.Repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    public boolean bookAppointment(AppointmentRequestDTO requestDTO) {
        boolean isBooked = appointmentRepository.
                existsByDoctor_DoctorIdAndTimeSlot_SlotIdAndAppointmentDate(
                        requestDTO.getDoctorId(), requestDTO.getSlotId(), requestDTO.getAppointmentDate()
                );

        if(isBooked){
            return false;
        }

        Doctor doctor = doctorRepository.findById(requestDTO.getDoctorId()).orElseThrow();
        Patient patient = patientRepository.findById(requestDTO.getPatientId()).orElseThrow();
        TimeSlot timeSlot = timeSlotRepository.findById(requestDTO.getSlotId()).orElseThrow();

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(requestDTO.getAppointmentDate());
        appointment.setTimeSlot(timeSlot);

        appointmentRepository.save(appointment);

        return true;
    }

    public List<AppointmentResponseDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();

        return appointments.stream()
                .map(appointment -> {
                    AppointmentResponseDTO dto = new AppointmentResponseDTO();
                    dto.setAppointmentId(appointment.getAppointmentId());
                    dto.setDoctorId(appointment.getDoctor().getDoctorId());
                    dto.setDoctorName(appointment.getDoctor().getName());
                    dto.setPatientId(appointment.getPatient().getPatientId());
                    dto.setPatientName(appointment.getPatient().getName());
                    dto.setAppointmentDate(appointment.getAppointmentDate());
                    dto.setStartTime(appointment.getTimeSlot().getStartTime());
                    dto.setEndTime(appointment.getTimeSlot().getEndTime());
                    return dto;
                }).collect(Collectors.toList());
    }
}
