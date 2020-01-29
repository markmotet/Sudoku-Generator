/*
markmotet
January 28th, 2020

DESCRIPTION:

*/

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class SudokuMaker {

    // Returns a randomized list of integers from 1 to size with no duplicates
    public static ArrayList<Integer> makeRandomizedArrayList(int size) {

        ArrayList<Integer> numbersArray = new ArrayList<Integer>();
        Random randomGenerator = new Random();

        while (numbersArray.size() < size) {
            int randomNumber = randomGenerator.nextInt(size) + 1;
            if (!numbersArray.contains(randomNumber)) {
                numbersArray.add(randomNumber);
            }
        }
        return numbersArray;
    }

    // Returns a randomized list of ordered pairs from (0, 0) to (size, size) with no duplicates
    public static ArrayList<int[]> makeRandomizedPairList(int size) {

        ArrayList<int[]> pairsArray = new ArrayList<int[]>();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                int[] pair = {row, col};
                pairsArray.add(pair);
            }
        }
        Collections.shuffle(pairsArray);
        return pairsArray;
    }

    // Returns true if a given number is NOT in the given row (i.e. the number is permitted in the row)
    public static boolean checkRow(int theNumber, int[][] theSudoku, int theRow) {
        for (int col = 0; col < 9; col++) {
            if (theSudoku[theRow][col] == theNumber) {
                return false;
            }
        }
        return true;
    }

    // Returns true if a given number is NOT in the given column (i.e. the number is permitted in the column)
    public static boolean checkCol(int theNumber, int[][] theSudoku, int theCol) {
        for (int row = 0; row < 9; row++) {
            if (theSudoku[row][theCol] == theNumber) {
                return false;
            }
        }
        return true;
    }

    // Returns true if a given number is NOT in the current 3x3 box (i.e. the number is permitted in the box)
    // THIS CODE IS DISGUSTING. I NEED TO FIND ANOTHER WAY THAT IS SIMPLER.
    public static boolean checkBox(int theNumber, int[][] theSudoku, int theRow, int theCol) {

        int whichBox;
        boolean truthValue = true;
        
        if (theRow >= 0 && theRow <= 2 && theCol >= 0 && theCol <= 2) {
            whichBox = 1; // Top left
        }
        else if (theRow >= 0 && theRow <= 2 && theCol >= 3 && theCol <= 5) {
            whichBox = 2; // Top middle
        }
        else if (theRow >= 0 && theRow <= 2 && theCol >= 6 && theCol <= 8) {
            whichBox = 3; // Top right
        }
        else if (theRow >= 3 && theRow <= 5 && theCol >= 0 && theCol <= 2) {
            whichBox = 4; // Middle left
        }
        else if (theRow >= 3 && theRow <= 5 && theCol >= 3 && theCol <= 5) {
            whichBox = 5; // Middle middle
        }
        else if (theRow >= 3 && theRow <= 5 && theCol >= 6 && theCol <= 8) {
            whichBox = 6; // Middle right
        }
        else if (theRow >= 6 && theRow <= 8 && theCol >= 0 && theCol <= 2) {
            whichBox = 7; // Bottom left
        }
        else if (theRow >= 6 && theRow <= 8 && theCol >= 3 && theCol <= 5) {
            whichBox = 8; // Bottom middle
        }
        else if (theRow >= 6 && theRow <= 8 && theCol >= 6 && theCol <= 8) {
            whichBox = 9; // Bottom right
        }
        else {
            whichBox = -1;
            System.out.println("Out of bounds :'(");
        }

        switch (whichBox) {
            case 1:
                for (int row = 0; row <= 2; row++) {
                    for (int col = 0; col <= 2 ; col++) {
                        if (theSudoku[row][col] == theNumber) {
                            truthValue = false;
                        }
                    }
                }
                break;
            case 2:
                for (int row = 0; row <= 2; row++) {
                    for (int col = 3; col <= 5 ; col++) {
                        if (theSudoku[row][col] == theNumber) {
                            truthValue = false;
                        }
                    }
                }
                break;
            case 3:
                for (int row = 0; row <= 2; row++) {
                    for (int col = 6; col <= 8 ; col++) {
                        if (theSudoku[row][col] == theNumber) {
                            truthValue = false;
                        }
                    }
                }
                break;
            case 4:
                for (int row = 3; row <= 5; row++) {
                    for (int col = 0; col <= 2 ; col++) {
                        if (theSudoku[row][col] == theNumber) {
                            truthValue = false;
                        }
                    }
                }
                break;
            case 5:
                for (int row = 3; row <= 5; row++) {
                    for (int col = 3; col <= 5 ; col++) {
                        if (theSudoku[row][col] == theNumber) {
                            truthValue = false;
                        }
                    }
                }
                break;
            case 6:
                for (int row = 3; row <= 5; row++) {
                    for (int col = 6; col <= 8 ; col++) {
                        if (theSudoku[row][col] == theNumber) {
                            truthValue = false;
                        }
                    }
                }
                break;
            case 7:
                for (int row = 6; row <= 8; row++) {
                    for (int col = 0; col <= 2 ; col++) {
                        if (theSudoku[row][col] == theNumber) {
                            truthValue = false;
                        }
                    }
                }
                break;
            case 8:
                for (int row = 6; row <= 8; row++) {
                    for (int col = 3; col <= 5 ; col++) {
                        if (theSudoku[row][col] == theNumber) {
                            truthValue = false;
                        }
                    }
                }
                break;
            case 9:
                for (int row = 6; row <= 8; row++) {
                    for (int col = 6; col <= 8 ; col++) {
                        if (theSudoku[row][col] == theNumber) {
                            truthValue = false;
                        }
                    }
                }
                break;
            default:
                System.out.println("You should not even be here!");
        }
        return truthValue;
    }

    // Returns true if a number is ok to be placed in a Sudoku box
    public static boolean checkAll(int theNumber, int[][] theSudoku, int theRow, int theCol) {
        if (checkRow(theNumber, theSudoku, theRow) &&
            checkCol(theNumber, theSudoku, theCol) &&
            checkBox(theNumber, theSudoku, theRow, theCol)) {
            return true;
        }
        return false;
    }

    // Fills the inputted Sudoku board as best as it can. This will often produce incomplete Sudokus
    public static void fillSudoku(int[][] theSudoku) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                ArrayList<Integer> randomList = makeRandomizedArrayList(9);
                int index = 0;
                boolean done = false;

                while (!done) {
                    if (index >= 9){
                        done = true;
                    }
                    else if (checkAll(randomList.get(index), theSudoku, row, col)) {
                        theSudoku[row][col] = randomList.get(index);
                        done = true;
                    }
                    else {
                        index++;
                    }
                }
            }
        }
    }

    // Print the Sudoku to the console in an easy to read format
    public static void printSudoku(int [][] theSudoku) {
        for (int row = 0; row < 9; row++) {
            if (row % 3 == 0) {
                System.out.println("+-----------+-----------+-----------+");
            }
            else {
                System.out.println("|           |           |           |");
            }
            for (int col = 0; col < 9; col++) {
                if (col == 0) {
                    System.out.print("| ");
                }
                else if (col % 3 == 0) {
                    System.out.print(" | ");
                }
                else {
                    System.out.print("   ");
                }

                if (theSudoku[row][col] == 0) {
                    System.out.print(" ");
                }
                else {
                    System.out.print(theSudoku[row][col]);
                }
            }
            System.out.print(" |");
            System.out.println();
        }
        System.out.println("+-----------+-----------+-----------+");
    }

    // Returns a completely filled in Sudoku board (i.e. an answer key)
    public static int[][] generateSudokuSolution() {

        int[][] testSudoku = new int[9][9];
        boolean truth = false;

        while (truth == false) {

            testSudoku = new int[9][9];
            fillSudoku(testSudoku);
            truth = true;

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (testSudoku[i][j] == 0) {
                        truth = false;
                    }
                }
            }
        }
        return testSudoku;
    }

    // Returns a Sudoku that is ready to be played based on difficulty
    public static void removeFromSudoku(int[][] theSudoku, int removeCount) {

        ArrayList<int[]> randomPairArray = makeRandomizedPairList(9);

        for (int numbersRemoved = 0; numbersRemoved < removeCount; numbersRemoved++) {
            if (theSudoku[randomPairArray.get(0)[0]][randomPairArray.get(0)[1]] == 0) {
                numbersRemoved--;
                randomPairArray.remove(0);
            }
            else {
                theSudoku[randomPairArray.get(0)[0]][randomPairArray.get(0)[1]] = 0;
                randomPairArray.remove(0);
            }
        }
    }

    public static int[][] copySudoku(int[][] theSudoku) {

        int[][] theSudokuCopy = new int[9][9];

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                theSudokuCopy[row][col] = theSudoku[row][col];
            }
        }
        return theSudokuCopy;
    }

    static int solutionCounter = 0; //Messy. Find an alternate way?

    // Solves the inputted Sudoku every possible way while incrementing solutionCounter for each solution
    public static boolean solveSudoku(int[][] theSudoku) {

        int[][] tempSudoku = copySudoku(theSudoku);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (tempSudoku[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (checkAll(num, tempSudoku, row, col)) {
                            tempSudoku[row][col] = num;
                            if (solveSudoku(tempSudoku)) {
                                tempSudoku[row][col] = 0;
                                solutionCounter++;
                                return false;
                            }
                            else {
                                tempSudoku[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int[][] testerSudoku = {
                { 3, 6, 8, 7, 0, 0, 2, 5, 4 },
                { 0, 7, 2, 0, 0, 0, 7, 6, 8 },
                { 0, 1, 9, 0, 0, 3, 7, 6, 8 },
                { 9, 2, 4, 0, 3, 0, 8, 7, 5 },
                { 7, 8, 3, 0, 0, 5, 1, 4, 6 },
                { 6, 5, 1, 4, 7, 8, 3, 2, 9 },
                { 1, 9, 5, 3, 6, 7, 4, 8, 2 },
                { 0, 3, 6, 0, 0, 0, 5, 9, 7 },
                { 0, 4, 7, 0, 5, 0, 6, 1, 3 }
        };

        int counter = 81;
        int min = 100;

        int[][] sudokuSolution = generateSudokuSolution();
        int[][] sudoku = copySudoku(sudokuSolution);
        int[][] sudokuCopy = new int[9][9];


        // 17 is the minimum number of starting numbers for a Sudoku to have a unique solution
        while (counter != 17){             
            counter = 0;

            // Removes a random number from a complete Sudoku until there are two solutions
            while (solutionCounter <= 1) {
                sudokuCopy = copySudoku(sudoku);
                removeFromSudoku(sudoku, 1);
                solutionCounter = 0;
                solveSudoku(sudoku);
            }
            // Reverts the Sudoku to have the last removed number so it has a unique solution again
            solutionCounter = 0;
            sudoku = copySudoku(sudokuCopy);

            // Counts the number of starting numbers of the Sudoku
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (sudokuCopy[i][j] != 0) {
                        counter++;
                    }
                }
            }

            // Progressively gets harder to generate a Sudoku with fewer starting numbers
            if (counter < min) {
                min = counter;
                System.out.println("_______________________________________________________________");
                System.out.println("Minimum numbers given so far:" + min);

                System.out.println("SOLUTION:");
                printSudoku(sudokuSolution);
                System.out.println("SUDOKU:");
                printSudoku(sudokuCopy);
            }
        }
    }
}