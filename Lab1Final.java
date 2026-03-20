/**
 * Write a java program to convert a string of bits to it's equivalent non-negative integer.
 * Author: Anaiah Apple
 * Date: 02/06/2026
 * Class: INT 2220 Computer Science II
 * Assignment: Lab #1
 */

import java.util.Scanner; // Import scanner for user input.

/**
 * Lab1Final class
 * This class represents a Java program that converts a string of bits to its equivalent non-negative integer.
 */
public class Lab1Final 
{

    /**
     * Base2_converter class
     * This class handles the conversion of a binary string to its decimal equivalent.
     */
    public static class Base2_converter
    {
       private String base2; // private data field for base 2 string
       private int base10; // private data field for base 10 integer

       /**
        * Constructor for Base2_converter
        * @param newBase2
        * @param newBase10
        */
       public Base2_converter (String newBase2, int newBase10)
       {
        base2 = newBase2; 
        base10 = newBase10;
       }

       /**
        * Processes the input binary string and converts it to a decimal integer.
        * @param none
        */
        public void processInput() 
        {
            base10 = 0; // Initializes base10 to 0 before processing.
            
            // Processes each bit from left to right.
            for (int i = 0; i < base2.length(); i++) 
                {
                int bit = base2.charAt(i) - '0'; // Get's the current bit as a character and converts to integer.
                base10 = base10 * 2 + bit; // Multiplies current result by 2 and adds the new bit.
            }
        }

        /**
         * Outputs the original binary string and its equivalent decimal integer.
         */
        public void output()
        {
            System.out.println("The original string you entered was " + base2 + "."); // Outputs the original binary string
            System.out.println("The equivalent integer is " + base10 + "."); // Outputs the equivalent decimal integer
        }
    }
/**
 * Main method to execute the program.
 * @param args
 */
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in); // Scanner object for user input.
        String base2Input = ""; // Declare variable outside the loop
        boolean validInput = false; // Flag to control the loop
        
        // Loop until valid input is received
        while (!validInput) 
        {
            System.out.print("Enter a string of bits (base 2): "); // Prompt the user for input
            base2Input = scanner.nextLine(); // Read the input string from the user.
            
            // Validate input: check if it contains only 0s and 1s
            if (!base2Input.matches("[01]+")) 
            {
                System.out.println("The string you entered was " + base2Input + "."); // Echo the invalid input
                System.out.println("Error -- The string should consist of 1s and 0s only."); // Prints error message for invalid input
            } 
            else 
            {
                validInput = true; // Set flag to exit loop
            }
        }
        
        // Process and output the valid input
        Base2_converter converter = new Base2_converter(base2Input, 0); // Create a Base2_converter object with the input string and initial decimal value 0
        converter.processInput(); // Processes the input to convert it to decimal.
        converter.output(); // Outputs the results.
        
        scanner.close(); // Close the scanner to prevent resource leaks.
    }
}
