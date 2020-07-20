package com.clinicalsapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicalsapi.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
