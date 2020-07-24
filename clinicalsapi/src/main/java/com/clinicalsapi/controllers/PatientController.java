package com.clinicalsapi.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.hibernate.annotations.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinicalsapi.entities.ClinicalData;
import com.clinicalsapi.entities.Patient;
import com.clinicalsapi.repos.PatientRepository;

@RestController
@RequestMapping("/api")
public class PatientController {

	private PatientRepository patientRepo;
	Map<String, String> filters = new HashMap<>();
	@Autowired
	PatientController(PatientRepository patientRepository) {
		this.patientRepo = patientRepository;
	}

	@RequestMapping(value = "/patients", method = RequestMethod.GET)
	public List<Patient> getPatients() {
		return patientRepo.findAll();
	}

	@RequestMapping(value = "/patients/{id}", method = RequestMethod.GET)
	public Patient getPatient(@PathVariable("id") int id) {
		return patientRepo.findById(id).get();
	}

	@RequestMapping(value = "/patients", method = RequestMethod.POST)
	public Patient savePatient(@RequestBody Patient patient) {
		System.out.println("LOL" + patient.getFirstName());
		return patientRepo.save(patient);
	}

	@RequestMapping(value = "/patients/anal/{id}", method = RequestMethod.GET)
	public Patient analyze(@PathVariable("id") int id) {
		Patient patient = patientRepo.findById(id).get();
		List<ClinicalData> clinicalData = patient.getClinicalData();
		ArrayList<ClinicalData> duplicateClinicalData = new ArrayList<>(clinicalData);
		for (ClinicalData eachEntry : duplicateClinicalData) {
			if(filters.containsKey(eachEntry.getComponentName())) {
				clinicalData.remove(eachEntry);
				continue;
			}else {
				filters.put(eachEntry.getComponentName(), null);
			}
			
			if (eachEntry.getComponentName().equals("hw")) {
				String[] heightWeigth = eachEntry.getComponentValue().split("/");
				if (heightWeigth != null && heightWeigth.length > 1) {
					float HeightInMetres = Float.parseFloat(heightWeigth[0]) * 0.4536F;
					float bmi = Float.parseFloat(heightWeigth[1]) / (HeightInMetres * HeightInMetres);
					ClinicalData bmiData = new ClinicalData();
					bmiData.setComponentName("bmi");
					bmiData.setComponentValue(Float.toString(bmi));
					clinicalData.add(bmiData);
				}
			}
		}
		filters.clear();
		return patient;
	}
}
