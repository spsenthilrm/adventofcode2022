package com.adventofcode22;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ElfCalories {

	private File getResourceFile(final String fileName) {
		URL url = this.getClass().getClassLoader().getResource(fileName);

		if (url == null) {
			throw new IllegalArgumentException(fileName + " is not found");
		}

		File file = new File(url.getFile());

		return file;
	}

	public static void main(String arg[]) {
		ElfCalories elf = new ElfCalories();
		elf.findElfWithMaxCalories("inputfile2.txt");
	}

	public void findElfWithMaxCalories(String fileName) {
		try {
			List<Integer> elfList = new ArrayList<>();
			File myObj = getResourceFile(fileName);
			Scanner myReader = new Scanner(myObj);
			int elfWithMaxCalorie = 0;
			int elfNumber = 0;
			int currElfWithCalorie = 1;
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (data.equals("") || data == null) {
					if (elfWithMaxCalorie < currElfWithCalorie) {
						elfWithMaxCalorie = currElfWithCalorie;

					}
					elfList.add(currElfWithCalorie);
					elfNumber += 1;
					currElfWithCalorie = 0;
					continue;
				}
				currElfWithCalorie += Integer.valueOf(data);
			}
			// Its the last line item which didnt reach empty space
			if (currElfWithCalorie > 0) {
				elfList.add(currElfWithCalorie);
				elfNumber += 1;
				currElfWithCalorie = 0;
			}
			myReader.close();
			System.out.println("Elf Number : " + elfNumber + " carrying Max Calorie : " + elfWithMaxCalorie);
			int resultOfTop3Cnt = sumOfTop3ElfHasMoreCalorie(elfList);
			System.out.println("Top 3 Elfs, sum of Calories " + resultOfTop3Cnt);
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	private int sumOfTop3ElfHasMoreCalorie(List<Integer> elfList) {
		Collections.sort(elfList, Collections.reverseOrder());
		int top3ElfCnt = 0;
		for (int i = 0; i < elfList.size(); i++) {
			if (i == 3)
				break;
			top3ElfCnt += elfList.get(i);
		}
		return top3ElfCnt;
	}
}
