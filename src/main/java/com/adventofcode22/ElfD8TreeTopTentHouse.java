package com.adventofcode22;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class ElfD8TreeTopTentHouse {
	private File getResourceFile(final String fileName) {
		URL url = this.getClass().getClassLoader().getResource(fileName);

		if (url == null) {
			throw new IllegalArgumentException(fileName + " is not found");
		}

		File file = new File(url.getFile());

		return file;
	}

	public static void main(String arg[]) {
		ElfD8TreeTopTentHouse elf = new ElfD8TreeTopTentHouse();
		List<String> dataList = elf.readFile("day8-inputfile2.txt");
//		elf.findVisibleTreesBasedOnHeight(dataList);
		elf.findBestSpotForTentHouse(dataList);

	}

	private void findBestSpotForTentHouse(List<String> dataList) {
		try {
			int[][] treesGrid = new int[dataList.get(0).length()][dataList.size()];

			int row = 0;
			int col = 0;
			for (String data : dataList) {
				String[] strVal = data.split("");
				col = 0;
				for (String str : strVal) {
					treesGrid[row][col] = Integer.valueOf(str);
					col++;
				}
				row++;
			}

			for (int i = 0; i < treesGrid.length; i++) {
				System.out.println();
				for (int j = 0; j < treesGrid.length; j++) {
					System.out.print(treesGrid[i][j] + "\t");
				}
			}

			System.out.println();
			System.out.println();

			HashMap<String, Integer> visibleTrees = new HashMap<>();
			for (int i = 0; i < treesGrid.length; i++) {
				for (int j = 0; j < treesGrid.length; j++) {
					if (i == 0 || j == 0 || (i + 1) == treesGrid.length || (j + 1) == treesGrid.length) {
						//Do nothing on the edges / first Row / last row
					} else {
						int existingResult = visibleTrees.get(i + " - " + j) != null ? visibleTrees.get(i + " - " + j) : 0;
						int result = scenicScore(i, j, treesGrid) + existingResult;
						visibleTrees.put("Row:"+i + "VS" + "Col:"+ j, result);
					}
				}
			}

			
			System.out.println("Trees List with Scneic Scores" + visibleTrees);
			Iterator<Entry<String, Integer>> itr = visibleTrees.entrySet().iterator();
			String spot = null;
			int maxValue = 0;
			while (itr.hasNext()) {
				Map.Entry<String, Integer> mapEntry = (Map.Entry<String, Integer>) itr.next();
				if (maxValue < mapEntry.getValue()) {
					spot = mapEntry.getKey();
					maxValue = mapEntry.getValue();
				}
			}
			
//			System.out.println("total Visible Trees : " + visibleTrees.size());
			System.out.println("Tent House max Entry spot : " + spot+ ",Scenic Score : "+maxValue);
		} catch (Exception e) {
			System.out.println("An error occurred." + e.getMessage());
			e.printStackTrace();
		}

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

	public void findVisibleTreesBasedOnHeight(List<String> dataList) {
		try {

			// int[][] treesGrid = new int[5][5];
			int[][] treesGrid = new int[dataList.get(0).length()][dataList.size()];

			int row = 0;
			int col = 0;
			for (String data : dataList) {
				String[] strVal = data.split("");
				col = 0;
				for (String str : strVal) {
					treesGrid[row][col] = Integer.valueOf(str);
					col++;
				}
				row++;
			}

			for (int i = 0; i < treesGrid.length; i++) {
				System.out.println();
				for (int j = 0; j < treesGrid.length; j++) {
					System.out.print(treesGrid[i][j] + "\t");
				}
			}

			System.out.println();
			System.out.println();

			List<Integer> visibleTrees = new ArrayList<>();
			for (int i = 0; i < treesGrid.length; i++) {
//				System.out.println();
				for (int j = 0; j < treesGrid.length; j++) {
					if (i == 0 || j == 0 || (i + 1) == treesGrid.length || (j + 1) == treesGrid.length) {
						visibleTrees.add(treesGrid[i][j]);
					} else {
						if (isVisibleInAnyDirection(i, j, treesGrid)) {
							visibleTrees.add(treesGrid[i][j]);
						}
					}
//					System.out.print(treesGrid[i][j]+"\t");
				}
			}

			System.out.println("Visible Trees List" + visibleTrees);
			System.out.println("total Visible Trees : " + visibleTrees.size());
		} catch (Exception e) {
			System.out.println("An error occurred." + e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean isVisibleInAnyDirection(int currTreeInRowIndex, int currTreeColumnIndex, int grid[][]) {
		if (currTreeInRowIndex - 1 < 0 || currTreeColumnIndex - 1 < 0)
			return false;
		int checkIndex = currTreeInRowIndex - 1;
		// Above Trees i.e, top left-right combination. But on the items current row and
		// column only
		// Up Direction
		boolean bigOrEquTreeFound = false;
		for (int i = checkIndex; i >= 0; i--) {
			System.out.println("Comparision between src ^^ : " + grid[currTreeInRowIndex][currTreeColumnIndex] + " VS "
					+ grid[i][currTreeColumnIndex]);
			if (grid[i][currTreeColumnIndex] >= grid[currTreeInRowIndex][currTreeColumnIndex]) {
				bigOrEquTreeFound = true;
				break;
			}
		}
		System.out.println("Up direction for the tree # in index " + currTreeInRowIndex + " " + currTreeColumnIndex
				+ " Value is " + grid[currTreeInRowIndex][currTreeColumnIndex]);
		System.out.println("Result is " + !bigOrEquTreeFound);
		if (!bigOrEquTreeFound)
			return true; // Visible from UP against all tree arranged based on Height on that Up
							// Direction

		bigOrEquTreeFound = false;
		// Down Direction
		checkIndex = currTreeInRowIndex + 1;
		for (int i = checkIndex; i < grid.length; i++) {
			System.out.println("Comparision between src DOWN DOWN: " + grid[currTreeInRowIndex][currTreeColumnIndex]
					+ " VS " + grid[i][currTreeColumnIndex]);
			if (grid[i][currTreeColumnIndex] >= grid[currTreeInRowIndex][currTreeColumnIndex]) {
				bigOrEquTreeFound = true;
				break;
			}
		}
		System.out.println("Down direction for the tree # in index " + currTreeInRowIndex + " " + currTreeColumnIndex
				+ " Value is " + grid[currTreeInRowIndex][currTreeColumnIndex]);
		System.out.println("Result is " + !bigOrEquTreeFound);
		if (!bigOrEquTreeFound)
			return true;

		bigOrEquTreeFound = false;
		// Left Direction
		checkIndex = currTreeColumnIndex - 1;
		for (int j = checkIndex; j >= 0; j--) {
			System.out.println("Comparision between src << : " + grid[currTreeInRowIndex][currTreeColumnIndex] + " VS "
					+ grid[j][currTreeColumnIndex]);
			if (grid[currTreeInRowIndex][j] >= grid[currTreeInRowIndex][currTreeColumnIndex]) {
				bigOrEquTreeFound = true;
				break;
			}
		}
		System.out.println("Left direction for the tree # in index " + currTreeInRowIndex + " " + currTreeColumnIndex
				+ " Value is " + grid[currTreeInRowIndex][currTreeColumnIndex]);
		System.out.println("Result is " + !bigOrEquTreeFound);
		if (!bigOrEquTreeFound)
			return true;

		bigOrEquTreeFound = false;
		// Right Direction
		checkIndex = currTreeColumnIndex + 1;
		for (int j = checkIndex; j < grid.length; j++) {
			System.out.println("Comparision between src >> : " + grid[currTreeInRowIndex][currTreeColumnIndex] + " VS "
					+ grid[j][currTreeColumnIndex]);
			if (grid[currTreeInRowIndex][j] >= grid[currTreeInRowIndex][currTreeColumnIndex]) {
				bigOrEquTreeFound = true;
				break;
			}
		}
		System.out.println("Right direction for the tree # in index " + currTreeInRowIndex + " " + currTreeColumnIndex
				+ " Value is " + grid[currTreeInRowIndex][currTreeColumnIndex]);
		System.out.println("Result is " + !bigOrEquTreeFound);
		if (!bigOrEquTreeFound)
			return true;

		return false;
	}

	public int scenicScore(int currTreeInRowIndex, int currTreeColumnIndex, int grid[][]) {
		if (currTreeInRowIndex - 1 < 0 || currTreeColumnIndex - 1 < 0)
			return 0;
		int checkIndex = currTreeInRowIndex - 1;
		int[] allDirections = { 0, 0, 0, 0 };
		// Above Trees i.e, top left-right combination. But on the items current row and
		// column only
		// Up Direction
		int scenicScoreTillBigOrEquTreeFound = 0;
		for (int i = checkIndex; i >= 0; i--) {
			System.out.println("Comparision between src ^^ : " + grid[currTreeInRowIndex][currTreeColumnIndex] + " VS "
					+ grid[i][currTreeColumnIndex]);
			scenicScoreTillBigOrEquTreeFound += 1;
			if (grid[i][currTreeColumnIndex] >= grid[currTreeInRowIndex][currTreeColumnIndex]) {
				break;
			}
		}
		System.out.println("Up direction for the tree # in index " + currTreeInRowIndex + " " + currTreeColumnIndex
				+ " Value is " + grid[currTreeInRowIndex][currTreeColumnIndex]);
		System.out.println("Sum Result is " + scenicScoreTillBigOrEquTreeFound);
//		if (!bigOrEquTreeFound)
//			return true; // Visible from UP against all tree arranged based on Height on that Up
//							// Direction

		allDirections[0] = scenicScoreTillBigOrEquTreeFound;
		scenicScoreTillBigOrEquTreeFound = 0;
		// Down Direction
		checkIndex = currTreeInRowIndex + 1;
		for (int i = checkIndex; i < grid.length; i++) {
			System.out.println("Comparision between src DOWN DOWN: " + grid[currTreeInRowIndex][currTreeColumnIndex]
					+ " VS " + grid[i][currTreeColumnIndex]);
			scenicScoreTillBigOrEquTreeFound += 1;
			if (grid[i][currTreeColumnIndex] >= grid[currTreeInRowIndex][currTreeColumnIndex]) {
				break;
			}
		}
		System.out.println("Down direction for the tree # in index " + currTreeInRowIndex + " " + currTreeColumnIndex
				+ " Value is " + grid[currTreeInRowIndex][currTreeColumnIndex]);
		System.out.println("Sum Result is " + scenicScoreTillBigOrEquTreeFound);
//		if (scenicScoreTillBigOrEquTreeFound)
//			return true;
		allDirections[1] = scenicScoreTillBigOrEquTreeFound;
		scenicScoreTillBigOrEquTreeFound = 0;
		// Left Direction
		checkIndex = currTreeColumnIndex - 1;
		for (int j = checkIndex; j >= 0; j--) {
			System.out.println("Comparision between src << : " + grid[currTreeInRowIndex][currTreeColumnIndex] + " VS "
					+ grid[j][currTreeColumnIndex]);

			scenicScoreTillBigOrEquTreeFound += 1;
			if (grid[currTreeInRowIndex][j] >= grid[currTreeInRowIndex][currTreeColumnIndex]) {
				break;
			}
		}
		System.out.println("Left direction for the tree # in index " + currTreeInRowIndex + " " + currTreeColumnIndex
				+ " Value is " + grid[currTreeInRowIndex][currTreeColumnIndex]);
		System.out.println("Sum Result is " + scenicScoreTillBigOrEquTreeFound);
//		if (scenicScoreTillBigOrEquTreeFound)
//			return true;
		allDirections[2] = scenicScoreTillBigOrEquTreeFound;
		scenicScoreTillBigOrEquTreeFound = 0;
		// Right Direction
		checkIndex = currTreeColumnIndex + 1;
		for (int j = checkIndex; j < grid.length; j++) {
			System.out.println("Comparision between src >> : " + grid[currTreeInRowIndex][currTreeColumnIndex] + " VS "
					+ grid[j][currTreeColumnIndex]);
			scenicScoreTillBigOrEquTreeFound += 1;
			if (grid[currTreeInRowIndex][j] >= grid[currTreeInRowIndex][currTreeColumnIndex]) {
				break;
			}
		}
		System.out.println("Right direction for the tree # in index " + currTreeInRowIndex + " " + currTreeColumnIndex
				+ " Value is " + grid[currTreeInRowIndex][currTreeColumnIndex]);
		System.out.println("Sum Result is " + scenicScoreTillBigOrEquTreeFound);
//		if (!scenicScoreTillBigOrEquTreeFound)
//			return true;
		allDirections[3] = scenicScoreTillBigOrEquTreeFound;
		return (allDirections[0] * allDirections[1] * allDirections[2] * allDirections[3]);
	}

}
