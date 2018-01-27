import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//  CodeCamp.java - CS314 Assignment 1 - Tester class

/*  Student information for assignment:
 *
 *  Name:   Scott Chase Waggener
 *  email address:  tidal@utexas.edu
 *  UTEID:  scw955
 *  Section 5 digit ID: 
 *  Grader name:
 *  Number of slip days used on this assignment:
 */


/* CS314 Students: place results of shared birthdays experiments in this comment.


*/

 
public class CodeCampTester {

    public static void main(String[] args){
        final String newline = System.getProperty("line.separator");
        Random r = new Random();

        
        // CS314 Students: add tests here.
        isPermutationTest1(100, 10000, true);  
        isPermutationTest1(100, 10000, false);
        isPermutationTest2();

        /* isPermutation() test 2 - randomized false stress test */
        
        

         /* Stress test permutation */


        // for(int i = 0; i < NUM_ELEMENTS; i++) {
        //     temp.add(r.nextInt());
        // }
        
        // a = intListToArray(temp);
        // Collections.shuffle(temp);
        // b = intListToArray(temp);
        
        // expectedBool = true;
        // actualBool = CodeCamp.isPermutation(a, b); 
        // System.out.println(newline + "Test 14 isPermutation: expected value: " 
        //         + expectedBool + ", actual value: " + actualBool);
        // if ( expectedBool == actualBool )
        //     System.out.println(" passed test 14, isPermutation");
        // else
        //     System.out.println(" ***** FAILED ***** test 14, isPermutation"); 



        /**
         * Compare birthday results to expected for 23 people and 365 days
         * This should be close to 1/2
         */
        // final int people = 23;
        // final int daysInYear = 365;
        // final int tries = 1000000; 

        // int results = 0;
        // for(int i = 0; i < tries; i++) {
        //     pairs = CodeCamp.sharedBirthdays(people, daysInYear);
        //     results += pairs & 0x01;
        // }
        
        // System.out.println(newline + "Test 28 shared birthdays - stress test. (Is solution slow?) : expected: " + tries/2);
        // System.out.println("Value returned: " + results);
        
    } // end of main method
    
    // pre: list != null
    // private static int[] intListToArray(List<Integer> list) {
    //     if(list == null)
    //         throw new IllegalArgumentException("list parameter may not be null.");
    //     int[] result = new int[list.size()];
    //     int arrayIndex = 0;
    //     for(int x : list) {
    //         result[arrayIndex++] = x;
    //     }
    //     return result;
    // }



    /**
     * @brief Compares the hamming distance
     */
    public static void hammingTest1() {

    }

    public static void hammingTest2() {

    }

    /**
     * @brief Tests isPermuatation with a randomly generated data set with an expected
     * result of true.
     * 
     * @param tries How many repetitions of the test to perform
     * @param size How many elements will be in each array
     * @param expectedBool Second array is scrambled if this is true, and a unique set if false
     * 
     * @pre tries > 0, size > 1
     * 
     */
    public static void isPermutationTest1(final int tries, final int size, final boolean expectedBool) {

        /* Fixed */
        final String test_header = "isPermutation() test 1 - randomized true stress test";
        final boolean actualBool;

        System.out.println(newline + test_header);

        /* Outer loop rerun test 'tries' times */
        for(int x = 0; x < tries; x++) {

            int[] original = new int[size];
            int[] scrambled = new int[size];

            /* Fill the original array */
            for(int i : original) { i = r.nextInt(); }

            /* Fill scrambled array */
            for(int i = 0; i < size; i++) {
                scrabled[i] = expectedBool ? original[i] : r.nextInt();
            }

            /* Handle scrambling of data if needed */
            if( expectedBool ) {

                for(int i = 0; i < size; i++) {
                    int temp = original[i];
                    int random_i = r.nextInt(size);
                    original[i] = original[random_i];
                    original[random_i] = temp; 
                }

            }

            actualBool = CodeCamp.isPermutation(original, scrambled);
            System.out.printf("Iteration %d - Expected: %b \t Actual: %b" + newline, x+1, expectedBool, actualBool);
            printResult(test_header, expectedBool, actualBool);
        }
    }


   


    /**
     * @brief Tests isPermutation using a premade dataset that will confuse approaches based 
     * on statistics (mean / standard deviation)
     * 
     */
    public static void isPermutationTest2() {

        String test_header = "isPermutation() test 2 - mean and standard deviation";
        final boolean expectedBool = false, actualBool;

        /* Both mean = 6, stdev = 4 */
        int[] perm1 = { 8, 8, 8, 0 };   
        int[] perm2 = { 4, 4, 4, 12 };
        actualBool = CodeCamp.isPermutation(perm1, perm2);

        System.out.println(newline + test_header);
        System.out.printf("Expected: %b \t Actual: %b" + newline, expectedBool, actualBool);
        printResult(test_header, expectedBool, actualBool);
    }

    public static void mostVowelsTest1() {
        
    }

    public static void mostVowelsTest2() {
        
    }

    public static void queensAreSafeTest1() {

    }

    public static void queensAreSafeTest2() {

    }

    public static void plotTest1() {

    }

    public static void plotTest2() {
        
    }

    private static void printResult(String header, boolean expected, boolean actual) {
        System.out.print( expected == actual ? "Passed test: " : " ***** FAILED *****" );
        System.out.println("\t" + header);
    }
}
