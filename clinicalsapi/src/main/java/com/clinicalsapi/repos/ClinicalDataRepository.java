package com.clinicalsapi.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicalsapi.entities.ClinicalData;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {

}
