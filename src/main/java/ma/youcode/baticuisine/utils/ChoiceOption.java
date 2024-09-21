package ma.youcode.baticuisine.utils;

import java.util.Scanner;

public class ChoiceOption {
    private static Scanner sc;

    static {
        sc = new Scanner(System.in);
    }
    public static int getChoice(int numberOptions) {

        while (true) {
            try {
                System.out.print(" Entrez votre choix : ");
                int input = sc.nextInt();
                if (input <= 0 || input > numberOptions) {
                    System.out.println("Choix invalide. Veuillez réessayer. ");
                    continue;
                }
                sc.nextLine();
                return input;
            }catch (Exception e){
                System.out.println("Une erreur est survenue. Veuillez réessayer.");
                sc.nextLine();

            }
        }
    }

}
