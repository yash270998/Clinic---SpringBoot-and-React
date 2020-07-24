package com.clinicalsapi.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinicalsapi.entities.ClinicalData;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {

	List<ClinicalData> findByPatientIdAndComponentNameOrderByMeasuredDateTime(int patientId, String componentName);

}
