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
         * We dont need to check letters that fall outside of the ASCII band of vowels ie 65-117
         *  i > 65 && i < 85 || i > 97 && i < 117
         * This means only 40 different characters need to be searched fully 
         *  
         * All the vowels are odd ASCII values
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
        final byte upper_to_lower = 97 - 65;
        final char vowels[] = { 'A', 'E', 'I', 'O', 'U' };

        /* Start from the first non null */
        while( list[ret] == null && ret < list.length ) {
            ret++;
        }
       
        /* Iterate over all strings in list */
        for(int i = ret; i < list.length; i++) {

            /* Skip null strings && strings shorter than current max vowel count */
            if( list[i] == null || list[i].length() < most_vowels ) continue;               
            
            /* Break the string into a char array */
            int current_string_vowels = 0;
            final char char_array[] = list[i].toCharArray();

            /* Iterate over all characters in the char array */
            for(char c : char_array) {

                /* If the ASCII char cant possibly be a vowel, skip it */
                if( c < 65 || c > 117 || (c > 85 && c < 97) ) continue;
                
                /* Otherwise, check if the char is a vowel, upper or lower case */
                for( char v : vowels ) {
                    if( ( c == v ) || ( c == v + upper_to_lower ) )
                        current_string_vowels++;
                }
            }

            /* Check if we found more vowels on this string */
            if( current_string_vowels > most_vowels ) {
                most_vowels = current_string_vowels;
                ret = i;
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

        /* How many queens are there - no dynamic allocation of positions */
        int queen_count = 0;
        for(int i = 0; i < board.length; i++) {
            for(char j : board[i]) {
                if( j == 'q') queen_count++;
            }
        }

        /* Where is each queen */
        int[][] queen_positions = new int[queen_count][2];
        int queen = 0;

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if( board[i][j] == 'q') {
                    queen_positions[queen][0] = i;
                    queen_positions[queen][1] = j;
                    queen++;
                }
            }
        }
       
        /* Can any attack eachother */
        for(int i = 0; i < queen_positions.length; i++) {
            for(int j = i + 1; j < queen_positions.length; j++) {
                if( queen_positions[i][0] == queen_positions[j][0] ) return false;
                if( queen_positions[i][1] == queen_positions[j][1] ) return false;

                /* Check if slope is +-1 to indicate diagonal*/
                final int delta_y = queen_positions[i][1] - queen_positions[j][1];
                final int delta_x = queen_positions[i][0] - queen_positions[j][0];
                if( Math.abs( delta_y ) == Math.abs( delta_x ) ) return false;
            }
        }

        return true;
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
        int max_value = city[0][0];
        int t = max_value;

        /* Try every possible rectangle size */
        for(int rect_row = 1; rect_row < city.length; rect_row++) {
            for(int rect_col = 1; rect_col < city[0].length; rect_col++) {

                /* Try every possible position for the rectangle */
                for(int row = 0; row + rect_col < city.length; row++) {
                    for(int col = 0; col + rect_row < city[0].length; col++) {

                        int value = valueOfPlot(city, row, col, rect_col, rect_row);
                        if(value > max_value) max_value = value;

                    }
                }


            }
        }


        /* Find the boundaries of that block

        /* Compute value of that block and compare with last known highest */

        return max_value;
    }

    private static int valueOfPlot(int[][] city, int x, int y, int xl, int yl) {
        int ret = 0;
        for(int row = x; row < x + xl; row++) {
            for(int col = y; col < y + yl; col++) {
                ret += city[row][col];
            }
        }
        return ret;
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