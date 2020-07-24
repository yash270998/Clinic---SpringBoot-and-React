package com.clinicalsapi.util;

import java.util.List;

import com.clinicalsapi.entities.ClinicalData;

public class BMICalculator {
	public static void calculateBMI(List<ClinicalData> clinicalData, ClinicalData eachEntry) {
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
}
