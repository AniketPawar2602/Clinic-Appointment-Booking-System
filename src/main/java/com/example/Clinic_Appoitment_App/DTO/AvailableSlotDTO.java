package com.example.Clinic_Appoitment_App.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class AvailableSlotDTO {
    private Long slotId;
    private LocalDate appointmentDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public AvailableSlotDTO() {
    }

    public AvailableSlotDTO(Long slotId, LocalDate appointmentDate, LocalTime startTime, LocalTime endTime) {
        this.slotId = slotId;
        this.appointmentDate = appointmentDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
