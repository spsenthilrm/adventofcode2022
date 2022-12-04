package com.adventofcode22;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ElfD4CampCleanup {
	private File getResourceFile(final String fileName) {
		URL url = this.getClass().getClassLoader().getResource(fileName);

		if (url == null) {
			throw new IllegalArgumentException(fileName + " is not found");
		}

		File file = new File(url.getFile());

		return file;
	}

	public static void main(String arg[]) {
		ElfD4CampCleanup elf = new ElfD4CampCleanup();
		List<String> dataList = elf.readFile("day4-inputfile2.txt");
		elf.findCampCleanupSectionFullOverlap(dataList);
		elf.findCampCleanupSectionPartialOverlap(dataList);
	}

	public List<String> readFile(String fileName) {
		List<String> dataList = new ArrayList<>();
		try {
			File myObj = getResourceFile(fileName);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				dataList.add(myReader.nextLine());
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return dataList;
	}

	public void findCampCleanupSectionFullOverlap(List<String> dataList) {
		try {
			int totalOverlapFound = 0;

			for (String data : dataList) {
				List<String> dataVal = Arrays.asList(data.split(","));
//				System.out.println(dataVal);
				String[] firstSection = dataVal.get(0).split("-");
				String[] secondSection = dataVal.get(1).split("-");

				if ((Integer.valueOf(firstSection[0]) <= Integer.valueOf(secondSection[0])
						&& Integer.valueOf(firstSection[1]) >= Integer.valueOf(secondSection[1]))
						|| (Integer.valueOf(secondSection[0]) <= Integer.valueOf(firstSection[0])
								&& Integer.valueOf(secondSection[1]) >= Integer.valueOf(firstSection[1]))) {
					totalOverlapFound++;
				}
			}

			System.out.println("total Full Overlap : " + totalOverlapFound);
		} catch (Exception e) {
			System.out.println("An error occurred." + e.getMessage());
			e.printStackTrace();
		}
	}

	public void findCampCleanupSectionPartialOverlap(List<String> dataList) {
		try {
			int totalOverlapFound = 0;
			for (String data : dataList) {
				List<String> dataVal = Arrays.asList(data.split(","));
//				System.out.println(dataVal);
				String[] firstSection = dataVal.get(0).split("-");
				String[] secondSection = dataVal.get(1).split("-");

				// Single section 1 item there is overlap. Using Between logic
				if ((Integer.valueOf(firstSection[0]) >= Integer.valueOf(secondSection[0])
						&& Integer.valueOf(firstSection[0]) <= Integer.valueOf(secondSection[1]))
						|| (Integer.valueOf(firstSection[1]) >= Integer.valueOf(secondSection[0])
								&& Integer.valueOf(firstSection[1]) <= Integer.valueOf(secondSection[1]))
						|| (Integer.valueOf(secondSection[0]) >= Integer.valueOf(firstSection[0])
								&& Integer.valueOf(secondSection[0]) <= Integer.valueOf(firstSection[1]))
						|| (Integer.valueOf(secondSection[1]) >= Integer.valueOf(firstSection[0])
								&& Integer.valueOf(secondSection[1]) <= Integer.valueOf(firstSection[1]))) {
					totalOverlapFound++;
				}
			}

			System.out.println("total Partial Overlap Found : " + totalOverlapFound);
		} catch (Exception e) {
			System.out.println("An error occurred." + e.getMessage());
			e.printStackTrace();
		}
	}

}
