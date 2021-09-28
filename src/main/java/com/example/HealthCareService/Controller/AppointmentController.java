package com.example.HealthCareService.Controller;

import com.example.HealthCareService.Model.Appointment;
import com.example.HealthCareService.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/appointment/register")
    public ResponseEntity<String> registerAppointment(@RequestBody Appointment appointment){
        Appointment registeredAppointment=appointmentService.registerAppointmentService(appointment);
        if(registeredAppointment==null){
            return new ResponseEntity<String >("Booking Failure", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String >("Booking Successful", HttpStatus.OK);
    }

    @GetMapping("/appointment/list")
    public ResponseEntity<ArrayList<Appointment>> getAppointmentList(){
        ArrayList<Appointment> appointmentList= appointmentService.getAppointmentListService();
        return new ResponseEntity<ArrayList<Appointment> >(appointmentList, HttpStatus.OK);
    }

    @GetMapping("/appointment/view/{id}")
    public ResponseEntity<Appointment>getAppointment(@PathVariable String appointmentId){
        Appointment appointment = appointmentService.getAppointmentService(appointmentId);
        if(appointment==null){
            return new ResponseEntity< >(null , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Appointment >(appointment, HttpStatus.OK);
    }

    @DeleteMapping("/appointment/delete/{id}")
    public ResponseEntity<String>deleteAppointment(@PathVariable String appointmentId){
        appointmentService.deleteAppointmentService(appointmentId);
        return new ResponseEntity<String >("Appointment Deleted", HttpStatus.OK);
    }
}
