package ma.youcode.baticuisine.utils;

import java.util.Scanner;

public class ChoiceOption {
    private static Scanner sc;

    static {
        sc = new Scanner(System.in);
    }
    public static int getChoice(int numberOptions) {

        while (true) {
            int input = sc.nextInt();
                if (input <= 0 || input > numberOptions) {
                    System.out.println("Choix invalide. Veuillez réessayer. ");
                    continue;
                }
                return input;
            }
    }

}
