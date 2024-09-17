package ma.youcode.baticuisine.views;

import ma.youcode.baticuisine.utils.ChoiceOption;
import ma.youcode.baticuisine.utils.Validator;

import java.util.Scanner;

public class CostumerView {

    private static final int NUMBER_OPTIONS = 3;
    private static int option;
    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static void view() {

        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ? ");
        System.out.println(" ___________________________________");
        System.out.println("|      Recherche de client          |");
        System.out.println("|___________________________________|");
        System.out.println("|                                   |");
        System.out.println("| 1. Chercher un client existant    |");
        System.out.println("| 2. Ajouter un nouveau client      |");
        System.out.println("| 3. Revenir au menu précédent      |");
        System.out.println("|___________________________________|");
        System.out.print(" Entrez votre choix : ");
        option = ChoiceOption.getChoice(4);
    }

    public static Menu run(Menu previousMenu) {

        do {
            view();
            switch (option) {
                case 1:
                    existCustomer();
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Retour au menu précédent...");
                    break;
                default:
                    System.out.print("Option invalide. Veuillez réessayer : ");
            }
        } while (option != NUMBER_OPTIONS);

        return previousMenu;
    }

    public static void existCustomer() {
        System.out.println("Vous avez choisi de chercher un client existant.");
        String name = Validator.validField("Veuillez entrer le nom du client : ", "[A-Za-z ]+", "Invalid name format. Please use only letters an spaces.");
//        Service costumer
    }
}
