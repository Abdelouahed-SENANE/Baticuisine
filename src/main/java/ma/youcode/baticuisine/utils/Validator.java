package ma.youcode.baticuisine.utils;

import java.util.Scanner;

public class Validator {

    private static final Scanner scanner = new Scanner(System.in);

    // This function for validate Datatype (String)
    public static String validField(String label  , String regex , String errorMessage) {

        while (true) {
            System.out.print(label);
            String input = scanner.next().trim();

            if (input.isEmpty() || input == null) {
                System.out.println("Input cannot be empty. Please try again.");
                continue;
            }
            if ( regex != null && !regex.isEmpty() ) {
                if (!input.matches(regex)) {
                    System.out.println(errorMessage);
                    continue;
                }
            }
            return input;
        }

    }
}
