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

public class ElfStonePaperScissor {

	private Map<String, Integer> handSymbolPoint;
	private Map<String, Integer> resultPoints;

	public enum GameStatus {
		WIN(6), LOSE(0), DRAW(3);

		int statusValue;

		GameStatus(int i) {
			statusValue = i;
		}

		public int getStatusValue() {
			return statusValue;
		}

	}

	private File getResourceFile(final String fileName) {
		URL url = this.getClass().getClassLoader().getResource(fileName);

		if (url == null) {
			throw new IllegalArgumentException(fileName + " is not found");
		}

		File file = new File(url.getFile());

		return file;
	}

	public static void main(String arg[]) {
		ElfStonePaperScissor elf = new ElfStonePaperScissor();
		elf.setGamePoints();
//		elf.calcGameScoreByElfPatternGiven("day2-inputfile1.txt");
		elf.calcGameScoreByRndNeedsToEnd("day2-inputfile2.txt");
	}

	public void setGamePoints() {
		// Player 2 points

		handSymbolPoint = new HashMap<>();
		handSymbolPoint.put("X", 1); // Rock
		handSymbolPoint.put("Y", 2); // Paper
		handSymbolPoint.put("Z", 3); // Scissor

		// Player 2 result Points with Player 1 competition
		resultPoints = new HashMap<>();
//		// Rock vs All
		resultPoints.put("A X", 3);
		resultPoints.put("A Y", 6);
		resultPoints.put("A Z", 0);

		// Paper vs All
		resultPoints.put("B X", 0);
		resultPoints.put("B Y", 3);
		resultPoints.put("B Z", 6);

		// Scissor vs All
		resultPoints.put("C X", 6);
		resultPoints.put("C Y", 0);
		resultPoints.put("C Z", 3);
	}

	/*
	 * "Anyway, the second column says how the round needs to end: X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win. Good luck!"
	 * 
	 * The total score is still calculated in the same way, but now you need to
	 * figure out what shape to choose so the round ends as indicated. The example
	 * above now goes like this:
	 * 
	 * In the first round, your opponent will choose Rock (A), and you need the
	 * round to end in a draw (Y), so you also choose Rock. This gives you a score
	 * of 1 + 3 = 4. In the second round, your opponent will choose Paper (B), and
	 * you choose Rock so you lose (X) with a score of 1 + 0 = 1. In the third
	 * round, you will defeat your opponent's Scissors with Rock for a score of 1 +
	 * 6 = 7. Now that you're correctly decrypting the ultra top secret strategy
	 * guide, you would get a total score of 12.
	 * 
	 * Following the Elf's instructions for the second column, what would your total
	 * score be if everything goes exactly according to your strategy guide?
	 */

	public void calcGameScoreByRndNeedsToEnd(String fileName) {
		try {
			File myObj = getResourceFile(fileName);
			Scanner myReader = new Scanner(myObj);
			int totalPointScored = 0;
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (resultPoints.get(data) != null) {
					// win 6, loss 0 and draw 3 points
					int gameResult = 0;
					// resultPoints.get(data);
					// For second player based on the column 2 value i.e.,
					// X means you need to lose
					// Y means you need to draw
					// Z means you need to win
					String player2OptedVal = data.split(" ")[1];
					final String player1OptedVal = data.split(" ")[0];

					List<Entry<String, Integer>> list = resultPoints.entrySet().stream()
							.filter(e -> e.getKey().startsWith(player1OptedVal + " ")).map(e -> e)
							.collect(Collectors.toList());

//					 System.out.println(list);
					String secondPlayerKeyFound = null;
					boolean matchFound = false;
					for (int i = 0; i < list.size(); i++) {
						switch (player2OptedVal) {
						case "X":
							if (list.get(i).getValue() == GameStatus.LOSE.getStatusValue()) {
								matchFound = true;
							}
							break;
						case "Y":
							if (list.get(i).getValue() == GameStatus.DRAW.getStatusValue()) {
								matchFound = true;
							}
							break;
						case "Z":
							if (list.get(i).getValue() == GameStatus.WIN.getStatusValue()) {
								matchFound = true;
							}
							break;
						}

						if (matchFound) {
							String keyName = list.get(i).getKey();
							secondPlayerKeyFound = keyName.split(" ")[1];
							gameResult = list.get(i).getValue();
							break;
						}

					}

					int symbolPoint = handSymbolPoint.get(secondPlayerKeyFound);

					System.out.println("symbolPoint " + symbolPoint + "\t+ game result " + gameResult);
					totalPointScored += gameResult + symbolPoint;
				}
			}
			myReader.close();
			System.out.println("totalPointScored : " + totalPointScored);
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void calcGameScoreByElfPatternGiven(String fileName) {
		try {
			File myObj = getResourceFile(fileName);
			Scanner myReader = new Scanner(myObj);
			int totalPointScored = 0;
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (resultPoints.get(data) != null) {
					// win 6, loss 0 and draw 3 points
					int gameResult = resultPoints.get(data);
					int symbolPoint = handSymbolPoint.get(data.split(" ")[1]);
					totalPointScored += gameResult + symbolPoint;
				}
			}
			myReader.close();
			System.out.println("totalPointScored : " + totalPointScored);
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
