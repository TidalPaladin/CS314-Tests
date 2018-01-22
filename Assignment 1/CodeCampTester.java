import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//  CodeCamp.java - CS314 Assignment 1 - Tester class

/*  Student information for assignment:
 *
 *  Name:
 *  email address:
 *  UTEID:
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
        String test_header;
        boolean expectedBool, actualBool;
        int[] a, b;
        final int perm_tries = 100;

        /* isPermutation() test 2 - randomized true stress test */
        for(int x = 0; x < perm_tries; x++) {
        test_header = "isPermutation() test 1 - randomized true stress test";
        final int perm_size = 100000;
        
        int[] rperm_1 = new int[perm_size];
        int[] rperm_2 = new int[perm_size];
        expectedBool = true;

        for(int i = 0; i < perm_size; i++) {
            rperm_1[i] = r.nextInt();
            rperm_2[i] = rperm_1[i];
        }

        for(int i = 0; i < perm_size; i++) {
            int temp = rperm_1[i];
            int random_i = r.nextInt(perm_size);
            rperm_1[i] = rperm_1[random_i];
            rperm_1[random_i] = temp; 
        }

        actualBool = CodeCamp.isPermutation(rperm_1, rperm_2);
        System.out.println(newline + test_header);
        System.out.printf("Expected: %b \t Actual: %b" + newline, expectedBool, actualBool);
        printResult(test_header, expectedBool, actualBool);


        /* isPermutation() test 2 - randomized false stress test */
        test_header = "isPermutation() test 2 - randomized false stress test";
        expectedBool = false;
        for(int i = 0; i < perm_size; i++) { rperm_1[i] = r.nextInt(); }

        actualBool = CodeCamp.isPermutation(rperm_1, rperm_2);
        System.out.println(newline + test_header);
        System.out.printf("Expected: %b \t Actual: %b" + newline, expectedBool, actualBool);
        printResult(test_header, expectedBool, actualBool);
        }

        /* isPermutation() test 1 - mean / standard deviation */
        /* This test is good at tripping up mean/stdev or hash based approaches */
        test_header = "isPermutation() test 3 - mean and standard deviation";
        int[] perm1 = { 8, 8, 8, 0 };
        int[] perm2 = { 4, 4, 4, 12 };
        expectedBool = false;
        actualBool = CodeCamp.isPermutation(perm1, perm2);
        System.out.println(newline + test_header);
        System.out.printf("Expected: %b \t Actual: %b" + newline, expectedBool, actualBool);
        printResult(test_header, expectedBool, actualBool);
        

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

    private static void printResult(String header, boolean expected, boolean actual) {
        System.out.print( expected == actual ? "Passed test: " : " ***** FAILED *****" );
        System.out.println("\t" + header);
    }
}
