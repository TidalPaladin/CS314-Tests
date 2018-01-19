//  CodeCamp.java - CS314 Assignment 1

/*  Student information for assignment:
 * 
 * replace <NAME> with your name.
 *
 *  On my honor, Scott Waggener, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name:   Scott Waggener
 *  email address:  tidal@utexas.edu
 *  UTEID:  scw955
 *  Section 5 digit ID: 
 *  Grader name:
 *  Number of slip days used on this assignment:
 */

import java.util.Random;

public class CodeCamp {
  
    /**
     * Determine the Hamming distance between two arrays of ints. 
     * Neither the parameter <tt>aList</tt> or
     * <tt>bList</tt> are altered as a result of this method.<br>
     * @param aList != null, aList.length == bList.length
     * @param bList != null, bList.length == aList.length
     * @return the Hamming Distance between the two arrays of ints.
     */    
    public static int hammingDistance(int[] aList, int[] bList){
        // check preconditions
        if (aList == null || bList == null || aList.length != bList.length) 
            throw new IllegalArgumentException("Violation of precondition: " +
            		"hammingDistance. neither parameter may equal null, arrays" +
            		" must be equal length.");
        
        /*CS314 STUDENTS: INSERT YOUR CODE HERE*/   /* Finished T(n) ~= N */
        int distance = 0;
        for(int i = 0; i < aList.length; i++) {
            distance += (aList[i] == bList[i]) ? 0 : 1;     // Increment distance for every mismatch
        }
        return distance;
    }
    
    
    /**
     * Determine if one array of ints is a permutation of another.
     * Neither the parameter <tt>listA</tt> or 
     * the parameter <tt>listB</tt> are altered as a result of this method.<br>
     * @param listA != null
     * @param listB != null
     * @return <tt>true</tt> if listB is a permutation of listA, 
     * <tt>false</tt> otherwise
     * 
    */
    public static boolean isPermutation(int[] listA, int[] listB) {
        // check preconditions
        if (listA == null || listB == null) 
            throw new IllegalArgumentException("Violation of precondition: " +
                    "isPermutation. neither parameter may equal null.");
           
                    
        // 1, 3, 2, 8               2, 2, 2, 8
        // 1, 9, 4, 64 = 78         4, 4, 4, 64 = 76

        /*CS314 STUDENTS: INSERT YOUR CODE HERE*/           /* Maybe Finished - T(n) ~= N */
        if(listA.length != listB.length) return false;      /* Permutations must be the same length */

        /* Compute the mean and standard deviation of listA */
        double listA_mean = mean(listA);
        double listA_stdev = stdev(listA, listA_mean);

        /* Compute the mean and standard deviation of listB */
        double listB_mean = mean(listB);
        double listB_stdev = stdev(listB, listB_mean);

        return listA_mean == listB_mean && listA_stdev == listB_stdev;

    }
    
    private static double mean(int[] list) {
        int ret = 0;
        for(int i : list) { ret += i; }
        return ret / list.length;
    }

    private static double stdev(int[] list, double mean) {
        int ret = 0;
        for(int i : list) { ret += Math.pow(i - mean, 2) / list.length;  }
        return Math.sqrt(ret);
    }
    
    /**
     * Determine the index of the String that 
     * has the largest number of vowels. 
     * Vowels are defined as <tt>'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 
     * 'U', and 'u'</tt>.
     * The parameter <tt>list</tt> is not altered as a result of this method.
     * <p>pre: <tt>list != null</tt>, <tt>list.length > 0</tt>, there is an least 1 non null element in list
     * <p>post: return the index of the non-null element in list that has the 
     * largest number of characters that are vowels.
     * If there is a tie return the index closest to zero. 
     * The empty String, "", has zero vowels.
     * It is possible for the maximum number of vowels to be 0.<br>
     * @param list the array to check
     * @return the index of the non-null element in list that has 
     * the largest number of vowels.
     */
    public static int mostVowels(String[] list) {
        // check preconditions
        if (list == null || list.length == 0 || !atLeastOneNonNull(list))  
            throw new IllegalArgumentException("Violation of precondition: " +
            		"mostVowels. parameter may not equal null and must contain " +
            		"at least one none null value.");
       

        // CS314 STUDENTS: ADD YOUR CODE HERE
        //  You can use all methods from the String class and native arrays.


        /**
         * We dont need to check strings whose length is less than the current maximum vowel count
         * 
         * Let vowels.length = V
         * Let average vowels[i].length = L
         * 
         * Bad brute force solution - count vowels for each string no matter what
         * Any case -> T(n) ~= n * L * V
         * 
         * Better brute force solution - dont check strings when length < most_vowels
         * Best case -> T(n) ~= (length of solution string) * V + n 
         * Worst case -> T(n) ~= n * L * V 
         * This should be better when searching for few possible targets or when L is small 
         * 
         * We could sort the array by length, n + nlog(n) between copy and sort
         * Then we check the ith letter for each string, incrementing i after each scan of the array,
         * and ignoring the portion of the array where vowels[i].length < most_vowels
         * Worst case -> n + nlog(n) + n * L * V = n * ( L * V + 1 + log(n) )
         */

        /* Declarations */
        int ret = 0, most_vowels = 0;
        final char vowels[] = { 'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u' };

        /* Iterate over all strings in list */
        for(int i = 0; i < list.length; i++) {

            if( list[i] == null ) continue;           /* Skip null strings */
            int count = 0;
            char char_array[] = list[i].toCharArray();    /* Make char array from string */

            /* Iterate over all characters in a string */
            for(char c : char_array) {
                if( contains(vowels, c) ) count++;
            }

            /* Check if we found more vowels on this string */
            if( count > most_vowels ) {
                most_vowels = count;    /* Update the max vowel number */
                ret = i;     /* Track index of most voweled string */
            }
            
        }

        return ret;
    }
    

    
    /**
     * Perform an experiment simulating the birthday problem.
     * Pick random birthdays for the given number of people. 
     * Return the number of pairs of people that share the
     * same birthday.<br>
     * @param numPeople The number of people in the experiment.
     * This value must be > 0
     * @param numDaysInYear The number of days in the year for this experiement.
     * This value must be > 0
     * @return The number of pairs of people that share a birthday 
     * after running the simulation.
     */
    public static int sharedBirthdays(int numPeople, int numDaysInYear) {
        // check preconditions
        if (numPeople <= 0 || numDaysInYear <= 0)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"sharedBirthdays. both parameters must be greater than 0. " +
                    "numPeople: " + numPeople + 
                    ", numDaysInYear: " + numDaysInYear);
        
        //CS314 STUDENTS: ADD YOUR CODE HERE /* T(n) = n^2 / 2

        /* Declarations */
        Random r = new Random();
        int birthdays[] = new int[numDaysInYear];
        int ret = 0;

        /**
         * This solution is slower but more in the spirit of the experiment
         * T(n) ~ numPeople
         * 
         */
        for(int i = 0; i < numPeople; i++) { birthdays[r.nextInt(numDaysInYear)]++; }
        
        /**
         * This solution is faster but less in the spirit of the experiment
         * T(n) ~ numDaysInYear
         * 
         */
        while(numPeople > 0) {
            int assign = r.nextInt(numPeople) + 1;              // Pick a random number of people to assign this birthday
            birthdays[r.nextInt(numDaysInYear)] += assign;      // Pick a random birthday to assign to
            numPeople -= assign;                                // Shrink sample size to those with an unchosen birthday
        }

         /** 
         * For a given number of people who share a birthday:\
         * Pairs = N choose 2 = N! / [ (N-2)! 2! ]
         * => Pairs = N * (N-1) / 2
         *
         */
        for(int i : birthdays) {
            ret +=  i * (i-1) / 2;
        }

        return ret;
    }
    
    
    /**
     * Determine if the chess board represented by board is a safe set up.
     * <p>pre: board != null, board.length > 0, board is a square matrix.
     * (In other words all rows in board have board.length columns.),
     * all elements of board == 'q' or '.'. 'q's represent queens, '.'s
     * represent open spaces.<br>
     * <p>post: return true if the configuration of board is safe,
     * that is no queen can attack any other queen on the board.
     * false otherwise.
     * the parameter <tt>board</tt> is not altered as a result of 
     * this method.<br>
     * @param board the chessboard
     * @return true if the configuration of board is safe,
     * that is no queen can attack any other queen on the board.
     * false otherwise.
    */
    public static boolean queensAreSafe(char[][] board) {
        char[] validChars = {'q', '.'};
        // check preconditions
        if (board == null || board.length == 0 || !isSquare(board) 
                || !onlyContains(board, validChars))
            throw new IllegalArgumentException("Violation of precondition: " +
            		"queensAreSafe. The board may not be null, must be square, " +
            		"and may only contain 'q's and '.'s");        
                
        //CS314 STUDENTS: ADD YOUR CODE HERE
        int len = board[0].length;          // Use on length, board is square
        int queen1_x = -1, queen1_y = -1;
        boolean safe = true;

        /* Find a queen with a linear search T(n) = 5n */
        for(int i = 0; i < len && queen1_x != -1; i++)
            for(int j = 0; j < len && queen1_y != -1; j++) {
                if(board[i][j] == 'q') {
                    queen1_x = i;
                    queen1_y = j;
                }
            }

        for(int base = 0; base < len; base++) {
          
        }

        /* See if another queen is attackable by the first queen */


        return false; //must change
    }

    /* Are the two queens able to attack eachother */
    private static boolean queenScan(int x1, int y1, int x2, int y2) {
       // return x1 == x2 || y1 == y2 || abs( (y2-y1) / (x2 - x1) ) == 1;
       return false;
    }

    private static boolean queenScan2(int x, int y, int length) {
        return false;
    }

    
    
    /**
     * Given a 2D array of ints return the value of the
     * most valuable contiguous sub rectangle in the 2D array.
     * The sub rectangle must be at least 1 by 1. 
     * <p>pre: <tt>mat != null, mat.length > 0, mat[0].length > 0,
     * mat</tt> is a rectangular matrix.
     * <p>post: return the value of the most valuable contigous sub rectangle
     * in <tt>city</tt>.<br>
     * @param city The 2D array of ints representing the value of
     * each block in a portion of a city.
     * @return return the value of the most valuable contigous sub rectangle
     * in <tt>city</tt>.
     */
    public static int getValueOfMostValuablePlot(int[][] city) {
        // check preconditions
        if (city == null || city.length == 0 || city[0].length == 0 
                || !isRectangular(city) )
            throw new IllegalArgumentException("Violation of precondition: " +
            		"getValueOfMostValuablePlot. The parameter may not be null," +
            		" must value at least one row and at least" +
                    " one column, and must be rectangular."); 
        

        //CS314 STUDENTS: ADD YOUR CODE HERE

        /* Find a valid block */


        /* Find the boundaries of that block

        /* Compute value of that block and compare with last known highest */

        return -1; //must change
    }
    
    
    // !!!!! ***** !!!!! ***** !!!!! ****** !!!!! ****** !!!!! ****** !!!!!!
    // CS314 STUDENTS: Put your birthday problem experiement code here:
    
    
    // pre: list != null
    // post: return true if at least one element in list is null
    // otherwise return false.
    private static boolean atLeastOneNonNull(String[] list) {
        // check precondition
        if(list == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"atLeastOneNonNull. parameter may not equal null.");
        
        boolean foundNonNull = false;
        int i = 0;
        while( !foundNonNull && i < list.length ){
            foundNonNull = list[i] != null;
            i++;
        }
        return foundNonNull;
    }
    
    
    /* pre: mat != null
    post: return true if mat is a square matrix, false otherwise
     */
    private static boolean isSquare(char[][] mat) {
        if(mat == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"isSquare. paremeter may not be null.");

        final int numRows = mat.length;
        int row = 0;
        boolean isSquare = true;
        while( isSquare && row < numRows ) {
            isSquare = ( mat[row] != null) && (mat[row].length == numRows);
            row++;
        }
        return isSquare;
    }
    
    
    /* pre: mat != null, valid != null
    post: return true if all elements in mat are one of the characters in valid
     */
    private static boolean onlyContains(char[][] mat, char[] valid) {
        // check preconditions
        if(mat == null || valid == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"onlyContains. paremeters may not be null.");
        
        int row = 0;
        boolean correct = true;
        while( correct && row < mat.length) {
            int col = 0;
            while(correct && col < mat[row].length) {
                correct = contains(valid, mat[row][col]);
                col++;
            }
            row++;
        }
        return correct;
    }
    
    
    /*  pre: list != null
        post: return true if c is in list
     */
    private static boolean contains(char[] list, char tgtChar) {
        // check preconditions
        if(list == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"contains. paremeter may not be null.");

        boolean found = false;
        int index = 0;
        while( !found && index < list.length) {
            found = list[index] == tgtChar;
            index++;
        }
        return found;
    }

    /**
     * Overload for arrays of ints
     */
    private static boolean contains(int[] list, int tgtInt) {
        // check preconditions
        if(list == null)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"contains. paremeter may not be null.");

        boolean found = false;
        int index = 0;
        while( !found && index < list.length) {
            found = list[index] == tgtInt;
            index++;
        }
        return found;
    }
   
    
     // pre: mat != null, mat.length > 0
     // post: return true if mat is rectangular
    private static boolean isRectangular(int[][] mat) {
        // check preconditions
        if(mat == null || mat.length == 0)
            throw new IllegalArgumentException("Violation of precondition: " +
            		"isRectangular. paremeter may not be null and must contain" +
            		" at least one row.");

        boolean correct = mat[0] != null;
        int row = 0;
        while(correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == mat[0].length);
            row++;
        }
        return correct;
    }
    
    // make constructor pirvate so no instances of CodeCamp can be created
    private CodeCamp() {
        
    }
}