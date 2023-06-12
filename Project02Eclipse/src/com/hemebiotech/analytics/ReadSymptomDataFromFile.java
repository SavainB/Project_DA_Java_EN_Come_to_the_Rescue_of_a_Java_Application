package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple brute force implementation
 *
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String filepath;
	
	/**
	 * 
	 * @param filepath a full or partial path to file with symptom strings in it, one per line
	 */
	public ReadSymptomDataFromFile (String filepath) {
		this.filepath = filepath;
	}
	
	@Override
	public List<String> GetSymptoms() {
		ArrayList<String> result = new ArrayList<String>();
		
		if (filepath != null) {
			try {
				BufferedReader reader = new BufferedReader (new FileReader(filepath));
				String line = reader.readLine();
				
				while (line != null) {
					result.add(line);
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	@Override
	public Map<String, Integer> CountSymptoms(List<String> file){
		Map<String, Integer> myMap = new HashMap<String, Integer>();
		if (file != null){
			for (String symptom : file) {
				// Vérifier si le symptôme existe déjà dans la map
				if (myMap.containsKey(symptom)) {
					// Si oui, incrémenter le compteur
					int count = myMap.get(symptom);
					myMap.put(symptom, count + 1);
				} else {
					// Sinon, ajouter le symptôme avec un compteur initial de 1
					myMap.put(symptom, 1);
				}
			}
			return myMap;
		}
		return myMap;
	}
}
