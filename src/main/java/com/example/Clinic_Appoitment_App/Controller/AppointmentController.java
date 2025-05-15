package com.example.Clinic_Appoitment_App.Controller;

import com.example.Clinic_Appoitment_App.DTO.AppointmentRequestDTO;
import com.example.Clinic_Appoitment_App.DTO.AppointmentResponseDTO;
import com.example.Clinic_Appoitment_App.DTO.AuthRequestDTO;
import com.example.Clinic_Appoitment_App.DTO.AvailableSlotDTO;
import com.example.Clinic_Appoitment_App.JwtUtil.JwtUtil;
import com.example.Clinic_Appoitment_App.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

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

    @GetMapping("/available-slots")
    public ResponseEntity<List<AvailableSlotDTO>> getAvailableSlots(
            @RequestParam Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate appointmentDate) {
        List<AvailableSlotDTO> availableSlots = appointmentService.getAvailableSlotsByDoctorAndDate(doctorId, appointmentDate);
        return ResponseEntity.ok(availableSlots);
    }

    @PostMapping("/getToken")
    public ResponseEntity<String> getToken(@RequestBody AuthRequestDTO authRequestDTO){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequestDTO.getUsername(),
                            authRequestDTO.getPassword()
                    )
            );
        }
        catch (AuthenticationException e){
            System.out.println("Authentication error: "+e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid User!!!");
        }

        String token = jwtUtil.generateToken(authRequestDTO.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
