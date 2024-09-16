package ma.youcode.baticuisine;

import ma.youcode.baticuisine.config.Database;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class App {
    private final static Database db;

    static {
        db = Database.getInstance();

    }

    public static void main(String[] args) {

        db.getConnection();

    }
}