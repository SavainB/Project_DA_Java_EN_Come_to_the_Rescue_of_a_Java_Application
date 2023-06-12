package com.hemebiotech.analytics;

import java.util.List;
import java.util.Map;

public class AnalyticsCounter {
	private static String filePath = "C:\\Users\\savain\\Documents\\OpenClassroom\\Blaneus_Savain_2_062023\\Project_DA_Java_EN_Come_to_the_Rescue_of_a_Java_Application\\Project02Eclipse\\symptoms.txt";
	public static void main(String args[]) throws Exception {
		try {
			IManageSymptom readSymptomDataFromFile = new ReadManageSymptomDataFromFile(filePath);

			List<String> symptoms = readSymptomDataFromFile.GetSymptoms();

			Map<String, Integer> symptomsSort = readSymptomDataFromFile.CountSymptoms(symptoms);

			readSymptomDataFromFile.CreateRapport(symptomsSort);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
