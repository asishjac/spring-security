package com.example.HealthCareService.Service;

import com.example.HealthCareService.Model.Patient;
import com.example.HealthCareService.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient registerPatientService(Patient patient){
        return patientRepository.save(patient);
    }

    public ArrayList<Patient>getPatientListService(){
        return (ArrayList<Patient>)patientRepository.findAll();
    }

    public Patient getPatientService(String patientId){
        return patientRepository.findById(patientId).get();
    }

    public void deletePatientService(String patientId){

        patientRepository.deleteById(patientId);
    }
}
