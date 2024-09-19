package ma.youcode.baticuisine.utils;

import java.util.Scanner;

public class Validator {

    private static final Scanner scanner = new Scanner(System.in);

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
    public static Double validDouble(String fieldName) {
        while (true) {
            System.out.print("Veuillez entrer " + fieldName + " : ");
            String input = scanner.nextLine().trim();
            Double value = Double.parseDouble(input);

            if (value.isNaN() || input == null) {
                System.out.println("La saisie ne peut pas être vide. Veuillez réessayer.");
                continue;
            }
            return value;
        }
    }


    public static Boolean validBoolean(String fieldName) {
        while (true) {
            System.out.println( fieldName + " : ");
            System.out.println("1 - Oui");
            System.out.println("2 - Non");
            Integer input = scanner.nextInt();

            if (input == null) {
                System.out.println("La saisie ne peut pas être vide. Veuillez réessayer.");
                continue;
            }
            if (input <= 0 || input > 2) {
                System.out.println("La valeur saisie doit être 1 ou 2. Veuillez réessayer.");
                continue;
            }
            scanner.nextLine();
            return (input == 1) ? true : false;
        }
    }

    public static Boolean validQuestion(String fieldName) {
        while (true) {
            System.out.print( fieldName + " : ");
            Integer input = scanner.nextInt();

            if (input == null) {
                System.out.println("La saisie ne peut pas être vide. Veuillez réessayer.");
                continue;
            }
            if (input <= 0 || input > 2) {
                System.out.println("La valeur saisie doit être 1 ou 2. Veuillez réessayer.");
                continue;
            }
            scanner.nextLine();
            return (input == 1) ? true : false;
        }
    }
    public static Double validCoeffcient(String fieldName) {
        while (true) {
            System.out.print( "Veuillez entrer "+fieldName + " : ");

            String input = scanner.nextLine().trim();
            Double value = Double.parseDouble(input);
            if (input == null) {
                System.out.println("La saisie ne peut pas être vide. Veuillez réessayer.");
                continue;
            }
            if (value < 1.0 || value > 2) {
                System.out.println("La valeur saisie doit être un coefficient entre 1.0 et 2. Veuillez réessayer.");
                continue;
            }
            return value;
        }
    }


    public static <E extends Enum<E>> E choiceEnum(Class<E> enumClass) {
        E[] enumConstants = enumClass.getEnumConstants();
        if (enumConstants == null || enumConstants.length == 0) {
            throw new IllegalArgumentException("La classe Enum doit avoir au moins une constante enum.");
        }
        System.out.println("Choisissez une option pour " + enumClass.getSimpleName() + " :");
        for (int i = 0; i < enumConstants.length; i++) {
            System.out.println((i + 1) + " - " + enumConstants[i].name().toLowerCase());
        }
        while (true) {
            try {
                int choix = scanner.nextInt();
                if (choix < 1 || choix > enumConstants.length) {
                    System.out.println("Choix invalide. Veuillez entrer un nombre entre 1 et " + enumConstants.length);
                    continue;
                }
                scanner.nextLine();
                return enumConstants[choix - 1];
            } catch (Exception e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre valide.");
                scanner.nextLine();
            }
        }
    }

}
