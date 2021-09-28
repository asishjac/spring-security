package com.example.HealthCareService.Controller;

import com.example.HealthCareService.Model.Patient;
import com.example.HealthCareService.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/patients/register")
    public ResponseEntity<String> registerPatient(@RequestBody Patient patient){
        Patient registeredPatient=patientService.registerPatientService(patient);
        if(registeredPatient==null){
            return new ResponseEntity<String >("Registration Failure", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String >("Registration Successful", HttpStatus.OK);
    }

    @GetMapping("/patients/list")
    public ResponseEntity<ArrayList<Patient>> getPatientList(){
       ArrayList<Patient> patientList= patientService.getPatientListService();
        return new ResponseEntity<ArrayList<Patient> >(patientList, HttpStatus.OK);
    }

    @GetMapping("/patients/view/{id}")
    public ResponseEntity<Patient>getPatient(@PathVariable String patientId){
        Patient patient = patientService.getPatientService(patientId);
        if(patient==null){
            return new ResponseEntity< >(null , HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Patient >(patient, HttpStatus.OK);
    }

    @DeleteMapping("/patients/delete/{id}")
    public ResponseEntity<String>deletePatient(@PathVariable String patientId){
        patientService.deletePatientService(patientId);
        return new ResponseEntity<String >("Patient Deleted", HttpStatus.OK);
    }
}
