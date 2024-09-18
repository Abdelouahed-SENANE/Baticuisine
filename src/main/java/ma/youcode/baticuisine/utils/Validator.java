package ma.youcode.baticuisine.utils;

import java.util.Scanner;

public class Validator {

    private static final Scanner scanner = new Scanner(System.in);

    // This function for validate Datatype (String)
    public static String validField(String fieldName, String regex) {

        while (true) {
            System.out.print("Veuillez entrer " + fieldName + " : ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty() || input == null) {
                System.out.println("La saisie ne peut pas être vide. Veuillez réessayer.");
                continue;
            }
            if ( regex != null && !regex.isEmpty() ) {
                if (!input.matches(regex)) {
                    System.out.println(fieldName + "est invalide. Veuillez respecter le format requis.");
                    continue;
                }
            }
            return input;
        }

    }


    public static Boolean validBoolean(String fieldName) {
        while (true) {
            System.out.println("Si est" + fieldName + " : ");
            System.out.println("1. Oui");
            System.out.println("2. Non");
            Integer input = scanner.nextInt();

            if (input == null) {
                System.out.println("La saisie ne peut pas être vide. Veuillez réessayer.");
                continue;
            }
            if (input <= 0 || input > 2) {
                System.out.println("La valeur saisie doit être 1 ou 2. Veuillez réessayer.");
                continue;
            }
            return (input == 1) ? true : false;
        }
    }
}
