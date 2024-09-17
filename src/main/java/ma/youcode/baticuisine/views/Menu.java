package ma.youcode.baticuisine.views;

import ma.youcode.baticuisine.utils.ChoiceOption;

import java.util.Scanner;

public class Menu {
    private static Scanner scanner;
    private int option;

    static {
        scanner = new Scanner(System.in);
    }

    public Menu run(Menu menu) {

        do {
            view();
            switch (option) {
                    case 1:
                        CostumerView.run(menu);
                        createProject();
                        break;
                    case 2:
                        break;
                     case 4:
                         System.out.println("Merci d'avoir utilisé l'application. À bientôt !");
                         System.exit(0);
                        break;
                default:
                    System.out.print("Option invalide. Veuillez réessayer : ");
            }
        }while (option != 4);

        return menu;

    }


    private void view() {
        System.out.println(" ___________________________________");
        System.out.println("|           Menu Principal          |");
        System.out.println("|___________________________________|");
        System.out.println("|                                   |");
        System.out.println("| 1. Créer un nouveau projet        |");
        System.out.println("| 2. Voir les projets existants     |");
        System.out.println("| 3. Calculer le coût d'un projet   |");
        System.out.println("| 4. Quitter                        |");
        System.out.println("|___________________________________|");
        System.out.print(" Entrez votre choix : ");
        option = ChoiceOption.getChoice(4);
    }


    public void createProject() {

    }

}
