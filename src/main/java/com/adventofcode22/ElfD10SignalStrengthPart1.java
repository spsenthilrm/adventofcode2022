package com.adventofcode22;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class ElfD10SignalStrengthPart1 {
	private File getResourceFile(final String fileName) {
		URL url = this.getClass().getClassLoader().getResource(fileName);

		if (url == null) {
			throw new IllegalArgumentException(fileName + " is not found");
		}

		File file = new File(url.getFile());

		return file;
	}

	public static void main(String arg[]) {
		ElfD10SignalStrengthPart1 elf = new ElfD10SignalStrengthPart1();
		List<String> dataList = elf.readFile("day10-inputfile2a.txt");
		elf.findSignalStrength(dataList);

	}

	private void findSignalStrength(List<String> dataList) {
		int X = 1;
		int noOfCycles = 1; // That time x is 1 -> Assumption
//		System.out.println(dataList);
		List<Integer> signalStrengths = new ArrayList<>();
		List<Integer> flagSeriesCompleted = new ArrayList<>();
		int totalSignalStrength= 0;
		for (String data : dataList) {			
			
//			System.out.println("Before Cmds that tiem x Value is " + X);
//			System.out.println("Result ::: \nnoOfCycles is " + noOfCycles + "\nX value is " + X);
//			System.out.println(" Total Signal Strengths " + totalSignalStrength);

//			if (noOfCycles >= 180) break;

			String[] strVal = data.split(" ");
			String operator = strVal[0];
//			System.out.println(operator);
			if (operator.equals("noop")) {
//				System.out.println("Current Cmd is noop and before / that tiem x Value is " + X +" and the no of cycles before "+noOfCycles);
				
				noOfCycles++;
//				System.out.println("Result ::: \nnoOfCycles is " + noOfCycles + "\nX value is " + X);
//				System.out.println("Current Cmd is noop and After that tiem x Value is " + X);
				
				totalSignalStrength = calcTotalSignalStrength(X, noOfCycles, signalStrengths, flagSeriesCompleted,
						totalSignalStrength);

//				System.out.println("Result Goes HEre ::: \nnoOfCycles is " + noOfCycles + "\nX value is " + X);
//				System.out.println(" list of Signal Strengths " + signalStrengths);
//				System.out.println(" Total Signal Strengths " + totalSignalStrength);
				
				continue;
			}
			int V = Integer.valueOf(strVal[1]);
			
//			System.out.println("Current Cmd is addx and V value is " + V + " that tiem x Value is " + X +" and the no of cycles before "+noOfCycles);
			noOfCycles += 1;

//			System.out.println("Result ::: \nnoOfCycles is " + noOfCycles + "\nX value is " + X);
//			System.out.println("Current Cmd is addx and V value is " + V + " After that tiem x Value is " + X);
//			
			
			totalSignalStrength = calcTotalSignalStrength(X, noOfCycles, signalStrengths, flagSeriesCompleted,
					totalSignalStrength);
//
//			System.out.println("Result Goes HEre ::: \nnoOfCycles is " + noOfCycles + "\nX value is " + X);
//			System.out.println(" list of Signal Strengths " + signalStrengths);
//			System.out.println(" Total Signal Strengths " + totalSignalStrength);
			
			noOfCycles += 1;
			X += V;

//			System.out.println("Result ::: \nnoOfCycles is " + noOfCycles + "\nX value is " + X);
//			System.out.println("Current Cmd is addx and V value is " + V + " After that tiem x Value is " + X);
//			
			
			totalSignalStrength = calcTotalSignalStrength(X, noOfCycles, signalStrengths, flagSeriesCompleted,
					totalSignalStrength);
			
//			System.out.println("Result Goes HEre ::: \nnoOfCycles is " + noOfCycles + "\nX value is " + X);
//			System.out.println(" list of Signal Strengths " + signalStrengths);
//			System.out.println(" Total Signal Strengths " + totalSignalStrength);
		}
		System.out.println("Result Goes Here ::: \nnoOfCycles is " + noOfCycles + "\nX value is " + X);
		System.out.println("List of Signal Strengths " + signalStrengths);
		System.out.println("Total Signal Strengths " + totalSignalStrength);
		//Sample Output for the input which i received.
		// list of Signal Strengths [420, 1260, 1600, 2940, 2340, 7920]
		// Total Signal Strengths 16480
	}

	private int calcTotalSignalStrength(int X, int noOfCycles, List<Integer> signalStrengths,
			List<Integer> flagSeriesCompleted, int totalSignalStrength) {
		if (noOfCycles == 20 && !flagSeriesCompleted.contains(20)) {
			signalStrengths.add(20 * X);
			totalSignalStrength += 20 * X;
			flagSeriesCompleted.add(20);
		} else if (noOfCycles == 60 && !flagSeriesCompleted.contains(60)) {
			signalStrengths.add(60 * X);
			totalSignalStrength += 60 * X;
			flagSeriesCompleted.add(60);
			
		} else if (noOfCycles == 100 && !flagSeriesCompleted.contains(100)) {
			signalStrengths.add(100 * X);
			totalSignalStrength += 100 * X;
			flagSeriesCompleted.add(100);
			
		} else if (noOfCycles == 140 && !flagSeriesCompleted.contains(140)) {
			signalStrengths.add(140 * X);
			totalSignalStrength += 140 * X;
			flagSeriesCompleted.add(140);
			
		} else if (noOfCycles == 180 && !flagSeriesCompleted.contains(180)) {
			signalStrengths.add(180 * X);
			totalSignalStrength += 180 * X;
			flagSeriesCompleted.add(180);
			
		} else if (noOfCycles == 220 && !flagSeriesCompleted.contains(220)) {
			signalStrengths.add(220 * X);
			totalSignalStrength += 220 * X;
			flagSeriesCompleted.add(220);
		}
		return totalSignalStrength;
	}

	public List<String> readFile(String fileName) {
		List<String> dataList = new ArrayList<>();
		try {
			File myObj = getResourceFile(fileName);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				dataList.add(myReader.nextLine());
			}
//			System.out.println(dataList);
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return dataList;
	}

}
