package ma.youcode.baticuisine;

import ma.youcode.baticuisine.config.Database;
import ma.youcode.baticuisine.views.Menu;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class App {

    public static void main(String[] args) {
        logo();
        Menu menu = new Menu();
        menu.run(menu);
    }

    public static void logo() {
        System.out.println("     ____        _   _        _               ");
        System.out.println("    |  _ \\      | | | |      (_)              ");
        System.out.println("    | |_) | __ _| |_| |_ _ __ _ _ __   __ _   ");
        System.out.println("    |  _ < / _` | __| __| '__| | '_ \\ / _` |  ");
        System.out.println("    | |_) | (_| | |_| |_| |  | | | | | (_| |  ");
        System.out.println("    |____/ \\__,_|\\__|\\__|_|  |_|_| |_|\\__, |  ");
        System.out.println("                                      __/ |  ");
        System.out.println("                                     |___/   ");
        System.out.println("Bienvenue dans l'application BATICUISINE ! Votre partenaire pour la rénovation et construction de cuisines modernes.1");
    }




}