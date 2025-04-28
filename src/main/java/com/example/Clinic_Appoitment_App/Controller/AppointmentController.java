package com.example.Clinic_Appoitment_App.Controller;

import com.example.Clinic_Appoitment_App.DTO.AppointmentRequestDTO;
import com.example.Clinic_Appoitment_App.DTO.AppointmentResponseDTO;
import com.example.Clinic_Appoitment_App.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book-appointment")
    public ResponseEntity<String> bookAppointment(@RequestBody AppointmentRequestDTO requestDTO){
        boolean success = appointmentService.bookAppointment(requestDTO);

        if (success){
            return ResponseEntity.status(HttpStatus.OK).body("Appointment booked successfully.");
        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Time slots already booked.");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointment(){
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.getAllAppointments());
    }
}
