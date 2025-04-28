package com.example.Clinic_Appoitment_App.Service;

import com.example.Clinic_Appoitment_App.Repository.TimeSlotRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;
}
