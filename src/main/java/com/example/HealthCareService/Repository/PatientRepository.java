package com.example.HealthCareService.Repository;

import com.example.HealthCareService.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,String > {
}
