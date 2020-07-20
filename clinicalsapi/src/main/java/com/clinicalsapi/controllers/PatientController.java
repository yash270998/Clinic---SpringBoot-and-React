package com.clinicalsapi.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinicalsapi.entities.Patient;
import com.clinicalsapi.repos.PatientRepository;

@RestController
@RequestMapping("/api")
public class PatientController {

	private PatientRepository patientRepo;

	@Autowired
	PatientController(PatientRepository patientRepository) {
		this.patientRepo = patientRepository;
	}

	@RequestMapping(value = "/patients", method = RequestMethod.GET)
	public List<Patient> getPatients() {
		return patientRepo.findAll();
	}
	@RequestMapping(value = "/patients/{id}",method = RequestMethod.GET)
	public Patient getPatient(@PathVariable("id") int id) {
		return patientRepo.findById(id).get();
	}
	@RequestMapping(value = "/patients",method = RequestMethod.POST)
	public Patient savePatient(@RequestBody Patient patient) {
		return patientRepo.save(patient);
	}
}
