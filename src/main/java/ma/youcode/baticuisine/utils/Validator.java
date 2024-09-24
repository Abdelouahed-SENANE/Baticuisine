package ma.youcode.baticuisine.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Validator {

    private static final Scanner scanner = new Scanner(System.in);

    public static String validField(String fieldName, String regex) {
        while (true) {
            try {
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

            }catch (Exception e) {
                System.out.println("Une erreur est survenue. Veuillez réessayer.");
            }
        }

    }
    public static Double validDouble(String fieldName) {
        while (true) {
            try{
                System.out.print("Veuillez entrer " + fieldName + " : ");
                String input = scanner.nextLine().trim();
                Double value = Double.parseDouble(input);

                if (value.isNaN() || input == null) {
                    System.out.println("La saisie ne peut pas être vide. Veuillez réessayer.");
                    continue;
                }
                return value;
            }catch (Exception e){
                System.out.println("Une erreur est survenue. Veuillez réessayer.");
            }
        }
    }

    public static LocalDate validDate(String fieldName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.print("Veuillez entrer " + fieldName + " (yyyy-mm-dd) : ");
            String input = scanner.nextLine();

            try {
                LocalDate date = LocalDate.parse(input, formatter);
                if (date.isBefore(LocalDate.now())) {
                    System.out.println("La date saisie est dépassée. Veuillez entrer une date future.");
                    continue;
                }
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Date invalide. Veuillez réessayer.");
            }
        }
    }

    public static Boolean validBoolean(String fieldName) {
        while (true) {
            try {
                System.out.println( fieldName + " : ");
                System.out.println("1 - Oui");
                System.out.println("2 - Non");
                System.out.print("Votre choix: ");

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
            }catch (Exception e){
                System.out.println("Une erreur est survenue. Veuillez réessayer.");
                scanner.nextLine();
            }
        }
    }

    public static Double validCoeffcient(String fieldName) {
        while (true) {
            try{
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
            }catch (Exception e) {
                System.out.println("Une erreur est survenue. Veuillez réessayer.");
            }
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

    public static int selectIndex(List<?> data , String prompt) {

        while (true) {
            try{
                System.out.print(prompt);
                int index = scanner.nextInt() - 1;

                if (index < 0 || index >= data.size()) {
                    System.out.println("Le nombre que vous avez choisi est hors de portée.");
                    continue;
                }
                scanner.nextLine();
                return index;

            }catch (Exception e){
                System.out.println("Une erreur est survenue. Veuillez réessayer.");
                scanner.nextLine();
            }

        }

    }

    public static String stringDefaultVal(String field, String defaultValue) {
        while (true) {
            System.out.println("Valeur précédente de " + field + " : " + defaultValue);
            System.out.print("Modifier " + field + " ? (Appuyez sur Entrée pour conserver la valeur précédente) : ");
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                return defaultValue;
            }

            return input;
        }
    }

    public static Double doubleDefaultVal(String field, Double defaultValue) {
        while (true) {
            System.out.println("Valeur précédente de " + field + " : " + defaultValue);
            System.out.print("Modifier " + field + " ? (Appuyez sur Entrée pour conserver la valeur précédente) : ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                return defaultValue;  // Return default value if input is empty
            }

            try {
                Double value = Double.parseDouble(input);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Veuillez entrer une valeur numérique valide.");
            }
        }
    }
}
