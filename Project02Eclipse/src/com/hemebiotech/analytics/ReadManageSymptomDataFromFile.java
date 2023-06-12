package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Simple brute force implementation
 *
 */
public class ReadManageSymptomDataFromFile implements IManageSymptom {

	private String filepath;
	
	/**
	 * 
	 * @param filepath a full or partial path to file with symptom strings in it, one per line
	 */
	public ReadManageSymptomDataFromFile(String filepath) {
		this.filepath = filepath;
	}
	
	@Override
	public List<String> GetSymptoms() {
		ArrayList<String> result = new ArrayList<>();
		
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
		Map<String, Integer> myMap = new HashMap<>();
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
			Map<String, Integer> sortedMap = new TreeMap<>(myMap);
			return sortedMap;
		}
		return myMap;
	}

	@Override
	public void CreateRapport( Map<String, Integer> map) throws IOException {
		FileWriter writer = new FileWriter("result.txt");
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();
			writer.write(key + "=" + value + System.lineSeparator());
		}
		writer.close();
	}
}
