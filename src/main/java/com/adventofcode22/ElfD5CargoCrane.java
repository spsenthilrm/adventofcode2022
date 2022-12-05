package com.adventofcode22;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import javax.sound.sampled.DataLine;

public class ElfD5CargoCrane {

    public static void main(String arg[]) {
        ElfD5CargoCrane elf = new ElfD5CargoCrane();
//        List<String> dataList = elf.readFile("5.txt");
//        HashMap<Integer, Stack<Character>> hs = new HashMap<Integer, Stack<Character>>();
//        ArrayList<Character> A1 = new ArrayList<Character>(Arrays.asList('B', 'Z', 'T'));
//        ArrayList<Character> A2 = new ArrayList<Character>(Arrays.asList('V', 'H', 'T', 'D', 'N'));
//        ArrayList<Character> A3 = new ArrayList<Character>(Arrays.asList('B', 'F', 'M', 'D'));
//        ArrayList<Character> A4 = new ArrayList<Character>(Arrays.asList('T', 'J', 'G', 'W', 'V', 'Q', 'L'));
//        ArrayList<Character> A5 = new ArrayList<Character>(Arrays.asList('W', 'D', 'G', 'P', 'V', 'F', 'Q', 'M'));
//        ArrayList<Character> A6 = new ArrayList<Character>(Arrays.asList('V', 'Z', 'Q', 'G', 'H', 'F', 'S'));
//        ArrayList<Character> A7 = new ArrayList<Character>(Arrays.asList('Z', 'S', 'N', 'R', 'L', 'T', 'C', 'W'));
//        ArrayList<Character> A8 = new ArrayList<Character>(Arrays.asList('Z', 'H', 'W', 'D', 'J', 'N', 'R', 'M'));
//        ArrayList<Character> A9 = new ArrayList<Character>(Arrays.asList('M', 'Q', 'L', 'F', 'D', 'S'));
        
        List<String> dataList = elf.readFile("5.txt");
        HashMap<Integer, Stack<Character>> hs = new HashMap<Integer, Stack<Character>>();
        ArrayList<Character> A1 = new ArrayList<Character>(Arrays.asList('S', 'L', 'W'));	
        ArrayList<Character> A2 = new ArrayList<Character>(Arrays.asList( 'J', 'T', 'N', 'Q'  ));
        ArrayList<Character> A3 = new ArrayList<Character>(Arrays.asList('S', 'C','H',  'F','J'   ));
        ArrayList<Character> A4 = new ArrayList<Character>(Arrays.asList('T', 'R', 'M', 'W', 'N', 'G', 'B'));
        ArrayList<Character> A5 = new ArrayList<Character>(Arrays.asList('T', 'R', 'L' , 'S', 'D', 'H', 'Q', 'B'));
        ArrayList<Character> A6 = new ArrayList<Character>(Arrays.asList('M', 'J', 'B','V', 'F','H', 'R', 'L'   ));
        ArrayList<Character> A7 = new ArrayList<Character>(Arrays.asList('D','W','R','N',  'J','M'));
        ArrayList<Character> A8 = new ArrayList<Character>(Arrays.asList('B', 'Z','T','F', 'H','N','D', 'J'));
        ArrayList<Character> A9 = new ArrayList<Character>(Arrays.asList('H','L','Q','N', 'B', 'F', 'T' ));
        
        Stack<Character> one = new Stack<Character>();
        one.addAll(A1);
        Stack<Character> two = new Stack<Character>();
        two.addAll(A2);
        Stack<Character> three = new Stack<Character>();
        three.addAll(A3);
        Stack<Character> four = new Stack<Character>();
        four.addAll(A4);
        Stack<Character> five = new Stack<Character>();
        five.addAll(A5);
        Stack<Character> six = new Stack<Character>();
        six.addAll(A6);
        Stack<Character> seven = new Stack<Character>();
        seven.addAll(A7);
        Stack<Character> eight = new Stack<Character>();
        eight.addAll(A8);
        Stack<Character> nine = new Stack<Character>();
        nine.addAll(A9);
       
        hs.put(1, one);
        hs.put(2, two);
        hs.put(3, three);
        hs.put(4, four);
        hs.put(5, five);
        hs.put(6, six);
        hs.put(7, seven);
        hs.put(8, eight);
        hs.put(9, nine);
        System.out.println(hs);
        
        // 0 0 0 0 0 T Z B
        // 0 0 0 N D T H V
        // 0 0 0 0 D M F B
        // 0 L Q V W G J T
        // M Q F V P G D W
        // 0 S F H G Q Z V
        // W C T L R N S Z
        // M R N J D W H Z
        // 0 0 S D F L Q M

        // HashMap<Integer, Stack<Character>> hs = new HashMap<Integer,
        // Stack<Character>>();
        // ArrayList<Character> A1 = new ArrayList<Character>(Arrays.asList('Z', 'N'));
        // ArrayList<Character> A2 = new ArrayList<Character>(Arrays.asList('M', 'C',
        // 'D'));
        // ArrayList<Character> A3 = new ArrayList<Character>(Arrays.asList('P'));

        // Stack<Character> one = new Stack<Character>();
        // one.addAll(A1);
        // Stack<Character> two = new Stack<Character>();
        // two.addAll(A2);
        // Stack<Character> three = new Stack<Character>();
        // three.addAll(A3);
        // System.out.println("Full" + one + " " + two + " " + three);
        // hs.put(1, one);
        // hs.put(2, two);
        // hs.put(3, three);

        // move 1 from 2 to 1
        // move 3 from 1 to 3
        // move 2 from 2 to 1
        // move 1 from 1 to 2
        for (String data : dataList) {
            System.out.println("data line "+data + "\n");

            String[] val = data.split("\\s");
            int loop = Integer.parseInt(val[0]);
            int fromstack = Integer.parseInt(val[1]);
            int toStack = Integer.parseInt(val[2]);

            if (loop ==1 ) {
	            for (int i = 0; i < loop; i++) {
	                char item = hs.get(fromstack).pop();
	                // System.out.println("popped " + item);
	                hs.get(toStack).push(item);
	                // System.out.println("Pushed stack " + hs.get(toStack));
	                System.out.println("Each move " + hs);
	            }
        	} else if (loop > 1) {
        		Stack<Character> tempStack = new Stack<>();
        		System.out.println("toStack "+ hs.get(toStack));
        		for (int i = 0; i < loop; i++) {
	                char item = hs.get(fromstack).pop();
	                tempStack.push(item); // D, N, Z
	                
//	                // System.out.println("popped " + item);
//	                hs.get(toStack).push(item);
//	                // System.out.println("Pushed stack " + hs.get(toStack));
//	                System.out.println("Each move " + hs);
	            }
        		System.out.println("tempstack "+ tempStack);
        		int fixedSize = tempStack.size();
        		for (int i = 0; i < fixedSize; i++) {
        			System.out.println(" temp stack size"+tempStack.size());
	                char item = tempStack.pop();
	                hs.get(toStack).push(item); // P, Z, N,  D
	            }
        		System.out.println("after merge  "+ hs.get(toStack));
            }

        }
        StringBuilder sb = new StringBuilder();
        for (int j = 1; j <= hs.size(); j++) {
            sb.append(hs.get(j).peek());
        }
        System.out.println("Part1 Answer " + sb.toString());

    }

    private File getResourceFile(final String fileName) {
        URL url = this.getClass().getClassLoader().getResource(fileName);

        if (url == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }

        File file = new File(url.getFile());

        return file;
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

    public void findCargoCrane(List<String> dataList) {
        try {
            int totalOverlapFound = 0;

            for (String data : dataList) {
                List<String> dataVal = Arrays.asList(data.split(","));
                // System.out.println(dataVal);
                String[] firstSection = dataVal.get(0).split("-");
                String[] secondSection = dataVal.get(1).split("-");

                if ((Integer.valueOf(firstSection[0]) <= Integer.valueOf(secondSection[0])
                        && Integer.valueOf(firstSection[1]) >= Integer.valueOf(secondSection[1]))
                        || (Integer.valueOf(secondSection[0]) <= Integer.valueOf(firstSection[0])
                                && Integer.valueOf(secondSection[1]) >= Integer.valueOf(firstSection[1]))) {
                    totalOverlapFound++;
                }
            }

            System.out.println("Cargo Grane : " + totalOverlapFound);
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
                // System.out.println(dataVal);
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

            System.out.println("Cargo Crane : " + totalOverlapFound);
        } catch (Exception e) {
            System.out.println("An error occurred." + e.getMessage());
            e.printStackTrace();
        }
    }

}