package com.example.HealthCareService.Service;

import com.example.HealthCareService.Model.Appointment;
import com.example.HealthCareService.Model.Patient;
import com.example.HealthCareService.Repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment registerAppointmentService(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public ArrayList<Appointment> getAppointmentListService(){
        return (ArrayList<Appointment>)appointmentRepository.findAll();
    }

    public Appointment getAppointmentService(String appointmentId){
        return appointmentRepository.findById(appointmentId).get();
    }

    public void deleteAppointmentService(String appointmentId){

        appointmentRepository.deleteById(appointmentId);
    }
}
