/**
 Anaiah Apple
 INT 2220 Computer Science 2
 February 27, 2026
*/

import java.util.LinkedList; // Import LinkedList for storing polynomial terms
import java.util.Scanner; // Import Scanner for user input

/**
 * Lab 2 - Linked Lists Practice
 * This program allows the user to input two polynomials, adds them together, and displays the result.
 * The polynomials are represented using a linked list of terms, where each term consists of a coefficient and an exponent.
 * The program includes methods for reading polynomials from user input, adding two polynomials, and displaying the resulting polynomial in a readable format.
 */
public class Lab2Submission 
{

    /**
     * Main method to execute the program
     * It prompts the user for two polynomials, processes the addition, and displays the results.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) 
    {
        // Loop to allow multiple polynomial additions (can be modified to exit after one addition if desired)
        while (true) 
        {
            // Use try-with-resources to ensure the Scanner is closed after use    
            try (
                // Input Data for first polynomial, prompting the user for input and reading it into a Polynomial object
                Scanner keyboard = new Scanner(System.in)) 
            {
                System.out.println("Enter first polynomial (e.g., 3x^4 + 2x^3 - 5x + 7): ");
                String polyInput1 = keyboard.nextLine();
                Polynomial poly1 = Polynomial.readPoly(polyInput1);

                // Input Data for second polynomial, prompting the user for input and reading it into a Polynomial object
                System.out.println("Enter second polynomial (e.g., 2x^3 - 5x + 5): ");
                String polyInput2 = keyboard.nextLine();;
                Polynomial poly2 = Polynomial.readPoly(polyInput2);

                // Processing: Add the two polynomials
                Polynomial result = Polynomial.addPolynomials(poly1, poly2);

                // Output Data: Display the polynomials and the result
                System.out.println("First Polynomial: " + poly1);
                System.out.println("Second Polynomial: " + poly2);
                System.out.println("The sum of the two polynomials is: " + result);
            }
            break; // Exit after one iteration for demonstration purposes
        }
    }

    /**
     * Class representing a term in a polynomial, consisting of a coefficient and an exponent.
     * @param coef The coefficient of the term
     * @param exponent The exponent of the term
     */
    public static class Term 
    {
        int coef; // Coefficient
        int exponent; // Exponent
        
        /**
         * Constructor for the Term class, initializing the coefficient and exponent of the term.
         * @param coef
         * @param exponent
         */
        public Term(int coef, int exponent) 
        {
            this.coef = coef;
            this.exponent = exponent;
        }
    }

    /**
     * Class representing a polynomial, which is a collection of terms. It provides methods for reading a polynomial from input,
     * @param terms A linked list of terms that make up the polynomial
     */
    public static class Polynomial 
    {
        LinkedList<Term> terms = new LinkedList<>(); // Linked list to store the terms of the polynomial
        
        // Method to get the list of terms in the polynomial
        LinkedList<Term> getTerms() 
        {
            return terms; // Return the list of terms in the polynomial
        }

        // Method to add a term to the polynomial, given a coefficient and an exponent
        LinkedList<Term> addTerm(int coef, int exponent) 
        {
            terms.add(new Term(coef, exponent)); // Add a new term to the list of terms in the polynomial
            return terms; // Return the updated list of terms after adding the new term
        }

        /**
         * Static method to read a polynomial from a string input, parse it, and create a Polynomial object with the corresponding terms.
         * @param polyInput
         * @return
         */
        public static Polynomial readPoly(String polyInput) 
        {
            // Create a new Polynomial object to store the parsed terms
            Polynomial polynomial = new Polynomial();
            
            // Remove all spaces first, then split on + and -
            polyInput = polyInput.replaceAll("\\s+", ""); // Remove all whitespace
            String[] termStrings = polyInput.replace("-", "+-").split("\\+");
           
            // Process each term string
            for (String termString : termStrings) 
            {
                termString = termString.trim();

                // Skip empty strings
                if (termString.isEmpty()) continue;
                int coef = 0;           
                int exponent = 0;

                // Parse coefficient and exponent. aka converting the string into an integer
                if (termString.contains("x")) 
                {
                    if (termString.contains("x^")) 
                    {
                        // Term with explicit exponent (e.g., "2x^3")
                        String[] parts = termString.split("x\\^"); // Split at 'x^'

                        // Handle coefficient
                        String coefStr = parts[0].trim();
                        coef = coefStr.isEmpty() || coefStr.equals("+") ? 1 : coefStr.equals("-") ? -1 : Integer.parseInt(coefStr); // Default to 1 or -1 if empty or just sign

                        // Handle exponent
                        exponent = Integer.parseInt(parts[1].trim());
                    } 
                    else 
                    {
                        // Term with exponent 1 (e.g., "2x" or "x")
                        String[] parts = termString.split("x"); // Split at 'x'
                        
                        // Handle coefficient
                        String coefStr = parts[0].trim();
                        coef = coefStr.isEmpty() || coefStr.equals("+") ? 1 : coefStr.equals("-") ? -1 : Integer.parseInt(coefStr); // Default to 1 or -1 if empty or just sign
                        
                        // Exponent is 1 for terms like "2x"
                        exponent = 1;
                    }
                } 
                else 
                {
                    // Constant term
                    coef = Integer.parseInt(termString.trim());

                    // Exponent is 0 for constant term
                    exponent = 0;
                }

                // Add term to polynomial
                polynomial.addTerm(coef, exponent);
            }

            return polynomial;
        }
        
        /**
         * Static method to add two polynomials together and return the resulting polynomial.
         * @param poly2
         * @return
         */
        public static Polynomial addPolynomials(Polynomial poly1, Polynomial poly2) 
        {
            Polynomial result = new Polynomial();
            
            // Add all terms from first polynomial
            for (Term term : poly1.terms) 
            {
                result.addTerm(term.coef, term.exponent);
            }
            
            // Add all terms from second polynomial
            for (Term term : poly2.terms) 
            {
                result.addTerm(term.coef, term.exponent);
            }
            
            // Combine like terms (terms with same exponent)
            Polynomial simplified = new Polynomial();
            boolean[] processed = new boolean[result.terms.size()];
            
            for (int i = 0; i < result.terms.size(); i++) 
            {
                if (processed[i]) continue;
                
                Term current = result.terms.get(i);
                int sumCoef = current.coef;
                
                // Find all other terms with same exponent
                for (int j = i + 1; j < result.terms.size(); j++) 
                {
                    if (!processed[j] && result.terms.get(j).exponent == current.exponent) 
                    {
                        sumCoef += result.terms.get(j).coef;
                        processed[j] = true;
                    }
                }
                
                // Only add terms with non-zero coefficients
                if (sumCoef != 0) 
                {
                    simplified.addTerm(sumCoef, current.exponent);
                }
                processed[i] = true;
            }
            
            return simplified;
        }
        
        @Override
        /**
         * Override the toString method to provide a string representation of the polynomial, which can be used for displaying the polynomial in a readable format. 
         */
        public String toString() 
        {
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            
            // Iterate through terms to build string representation
            for (Term term : terms)
            {
                // Handle positive coefficients
                if (!first && term.coef > 0) 
                {
                    sb.append(" + ");
                } 
                /*If the exponent of one is smaller than the exponent of the other, 
                    then insert the larger one into the result and advance that list's iterator.*/
                    
                // Handle negative coefficients
                else if (term.coef < 0) 
                {
                    sb.append(" - ");
                    
                    // Make coefficient positive for display
                    term = new Term(-term.coef, term.exponent);
                } 
                // handle first term without leading plus sign
                else if (!first) 
                {
                    sb.append(" + ");
                }
                
                /*If the exponents are equal, 
                then create a new term with that exponent and the sum of the two coefficients, 
                and advance both iterators.*/

                // Handle coefficient and exponent display
                if (term.exponent == 0) 
                {
                    sb.append(term.coef);
                } 
                // Handle exponent cases
                else if (term.exponent == 1) 
                {
                    sb.append(term.coef).append("x");
                } 
                // General case
                else 
                {
                    sb.append(term.coef).append("x^").append(term.exponent);
                }
                // Set first to false after processing the first term
                first = false;
            }
            return sb.toString(); // Return the string representation of the polynomial
        }
    }

    /**
     * Static method to display a polynomial, which takes a Polynomial object as input and prints its string representation to the console. 
     */
    public static void displayPolynomial(Polynomial poly) 
    {
        // Print the polynomial using its toString method
        System.out.println("Polynomial: " + poly); 
    }

}