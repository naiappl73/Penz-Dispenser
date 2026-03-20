/**
 * Name: Anaiah Apple
 * File: Lab3PezDispenser.java
 * Date: 03/19/2026
 * Description: This program simulates a Pez dispenser. The user can input up to 12 candy colors, and the program will remove all yellow candies while preserving the order of the remaining candies.
 * The program also validates that all input colors are from a predefined list of allowed colors.
 */
import java.util.Scanner; // scanner for user input
import java.util.Stack; // stack for temporary storage of candies
import java.util.ArrayList; // array list for storing the original input and the final result after removing yellow candies

/**
 * Lab3PezDispenser is the main class, allowing the user to input candy colors, validate them, and process the list to remove yellow candies while maintaining the order of the remaining candies.
 * @param args Command-line arguments (not used)
 */
public class Lab3PezDispenser
{
    static String[] allowedColors = {"green", "yellow", "red", "pink", "orange"}; // List of allowed candy colors

    /**
     * The main method prompts the user to input candy colors, validates the input, and processes the list to remove yellow candies while preserving the order of the remaining candies. Finally, it displays the results.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args){

        Scanner keyboard = new Scanner(System.in); // scanner for user input
        ArrayList<String> candies = new ArrayList<>(); // list to store the original input of candy colors

        System.out.println(); // Blank line for better formatting
        System.out.println("Welcome to the Pez Dispenser!"); // Welcome message for the user
        System.out.println();

        // Prompting the user for input
        System.out.println("Instructions:");
        System.out.println("Enter up to 12 candy colors (press Enter after each one).");
        System.out.println("The colors we have are: Green, Yellow, Red, Pink, Orange");
        System.out.println("Type 'done' when finished.\n");

        // while loop to ensure valid input is collected from the user. It will keep prompting until all entered colors are valid.
        boolean validInput = false;
        while (!validInput){
            candies = new ArrayList<>(); // reset the list for retry
            // Nested while loop to collect candy colors from the user, allowing up to 12 entries or until the user types "done"
            while (candies.size() < 12){
                System.out.print("Enter candy #" + (candies.size() + 1) + ": ");
                String input = keyboard.nextLine().trim();

                // Lets the user stop early by typing "done"
                if (input.equalsIgnoreCase("done")){
                    break;
                }

                candies.add(input); // Add the input candy color to the list
            }

            System.out.println();

            // Displays the input list back to the user
            System.out.println("Here's the input list:");
            System.out.print("     ");

            // Loop to print the candy colors in the input list, separated by commas
            for (int i = 0; i < candies.size(); i++){
                System.out.print(candies.get(i));
                // Print a comma after each candy except the last one
                if (i < candies.size() - 1){
                    System.out.print(", ");
                }
            }
            System.out.println("\n");

            // Validates that all input colors are from the allowed list.
            if (allColorsAreValid(candies)){
                validInput = true;
            }
            else{
                System.out.println("Error! The input list includes non-allowed candy colors.");
                System.out.println();
                System.out.println("The only allowed candy colors are: Green, Yellow, Red, Pink, and Orange.");
                System.out.println("Please try again.\n");
            }
        }

        Stack<String> tempStack    = new Stack<>(); // stack for temporary storage of non-yellow candies
        Stack<String> reverseStack = new Stack<>(); // stack for reversing the order of non-yellow candies
        ArrayList<String> result   = new ArrayList<>(); // list to store the final result after removing yellow candies

        // Push non-yellow candies onto tempStack
        for (String candy : candies){
            if (!candy.equalsIgnoreCase("yellow")){
                tempStack.push(candy);
            }
        }

        // tempStack is reversed, so it flips using a second stack
        while (!tempStack.isEmpty()){
            reverseStack.push(tempStack.pop());
        }

        // Pop from reverseStack to result list, which will maintain the original order of non-yellow candies
        while (!reverseStack.isEmpty()){
            result.add(reverseStack.pop());
        }

        // Displays the output list after removing yellow candies
        System.out.println("Here's the output, after removing the yellow-colored candies:");
        System.out.print("     ");

        // If the result list is empty, it means all candies were yellow. Otherwise, it will print the remaining candies in the result list.
        if (result.isEmpty()){
            System.out.println("(No candies left, they were all yellow! \"And it was all yellowwww\" (from 'Yellow' by ColdPlay)");
        }
        else {
            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i));
                if (i < result.size() - 1){
                    System.out.print(", ");
                }
            } System.out.println();
        }

        keyboard.close();
    }

    // Checks if every candy in the list is a valid color
    static boolean allColorsAreValid(ArrayList<String> candies) {

        // Loop through each candy color in the list and check if it is an allowed color. If any candy is not allowed, return false.
        for (String candy : candies) {
            if (!isAllowedColor(candy)) {
                return false;
            }
        }
        return true;
    }

    // Checks if one single candy color is valid
    static boolean isAllowedColor(String color) {

        // Loop through the allowed colors and check if the input color matches any of them (case-insensitive).
        // If a match is found, return true. If no match is found after checking all allowed colors, return false.
        for (String allowed : allowedColors) {
            if (color.equalsIgnoreCase(allowed)) {
                return true;
            }
        } return false;
    }
}
