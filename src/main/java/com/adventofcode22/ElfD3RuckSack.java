package com.adventofcode22;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ElfD3RuckSack {

	private Map<Character, Integer> alphabetPoints;
	private Map<Character, Integer> firstCompartment;
	private Map<Character, Integer> secondCompartment;

	private File getResourceFile(final String fileName) {
		URL url = this.getClass().getClassLoader().getResource(fileName);

		if (url == null) {
			throw new IllegalArgumentException(fileName + " is not found");
		}

		File file = new File(url.getFile());

		return file;
	}

	public static void main(String arg[]) {
		ElfD3RuckSack elf = new ElfD3RuckSack();
		elf.setGamePoints();
		elf.ruckSack("day3-inputfile2.txt");
		elf.ruckSack3RowsAsAElfGroup("day3-inputfile2.txt");
	}

	public void setGamePoints() {
		alphabetPoints = new HashMap<Character, Integer>();
		for (int i = 97 ; i <= 122; i++) {
			alphabetPoints.put((char)i, i-96);
		}
		for (int i = 65 ; i <= 90; i++) {
			alphabetPoints.put((char)i, i-38);
		}
		
	}

	public void ruckSack(String fileName) {
		try {
			File myObj = getResourceFile(fileName);
			Scanner myReader = new Scanner(myObj);
			int totalPointScored = 0;
			while (myReader.hasNextLine()) {
				firstCompartment = new HashMap<Character, Integer>();
				secondCompartment = new HashMap<Character, Integer>();
				
				String data = myReader.nextLine();
				int findMid = data.length() / 2;
				String str1 = data.substring(0, findMid);
				String str2 = data.substring(findMid);
				
				char[] firstContChars = str1.toCharArray();
				char[] secondContChars = str2.toCharArray();
				for (char firstContChar : firstContChars) {
					firstCompartment.put(firstContChar, 1);
				}
				for (char secondContChar : secondContChars) {
					if (firstCompartment.containsKey(secondContChar)) {
//						System.out.println(" match found "+secondContChar);
						totalPointScored += alphabetPoints.get(secondContChar);
						break;
					}
				}
			}
			myReader.close();
			System.out.println("totalPointScored Compared by Two Compartment(s) on SameRow : " + totalPointScored);
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void ruckSack3RowsAsAElfGroup(String fileName) {
		try {
		File myObj = getResourceFile(fileName);
		Scanner myReader = new Scanner(myObj);
		int totalPointScored = 0;
		int rowCounter = 0;
		char[] firstRowContChars = null;
		char[] secondRowContChars = null;
		Map<Character, Integer> row1 = null, row2 = null, row3 = null;
		
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			if (rowCounter == 0) {
				row1 = new HashMap<Character, Integer>();
				row2 = new HashMap<Character, Integer>();
				
				firstRowContChars = data.toCharArray();
				rowCounter++;
			} else if (rowCounter == 1 ) {
				secondRowContChars = data.toCharArray();
				rowCounter++;
			} else if (rowCounter == 2 ) {
				char[] thirdRowContChars = data.toCharArray();
				for (char firstContChar : firstRowContChars) {
					row1.put(firstContChar, 1);
				}
				for (char secondContChar : secondRowContChars) {
					row2.put(secondContChar, 1);
				}
				
				for (char thirdRowChar : thirdRowContChars) {
					if (row1.containsKey(thirdRowChar) && 
							row2.containsKey(thirdRowChar)) {
//						System.out.println(" match found "+thirdContChar);
						totalPointScored += alphabetPoints.get(thirdRowChar);
						break;
					}
				}
				
				// Reset all for the next set of 3 rows processing
				rowCounter = 0;
				row1 = null;
				row2 = null;
			}
			
		}
		myReader.close();
		System.out.println("totalPointScored Grouped by 3 Rows : " + totalPointScored);
	} catch (FileNotFoundException e) {
		System.out.println("An error occurred.");
		e.printStackTrace();
	}
}
}
