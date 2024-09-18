package ma.youcode.baticuisine.views;

import ma.youcode.baticuisine.entities.Customer;
import ma.youcode.baticuisine.services.CustomerService;
import ma.youcode.baticuisine.services.implementations.CustomerServiceImp;
import ma.youcode.baticuisine.utils.ChoiceOption;
import ma.youcode.baticuisine.utils.Validator;

import java.util.Optional;
import java.util.Scanner;

public class CostumerView {

    private static final int NUMBER_OPTIONS = 3;
    private static int option;
    private static final Scanner scanner;
    private static final CustomerService customerService;

    static {
        scanner = new Scanner(System.in);
        customerService = new CustomerServiceImp();
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
                    createCustomer();
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

        String customerName = Validator.validField("nom du client recherche", "[A-Za-z ]+");
        Optional<Customer> customer = customerService.getCustomerByName(customerName);
        customer.ifPresentOrElse(CostumerView::displayCard, () -> System.out.println("Customer not found"));

    }

    public static void createCustomer() {
        String customerName = Validator.validField("nom du client", "[A-Za-z ]+");
        String customerAddress = Validator.validField("adresse du client ", null);
        String customerPhone = Validator.validField("telephone du client ", "^[0-9(.)-]+$");
        Boolean isProfesstional = Validator.validBoolean("un professional");

        Customer newCustomer = new Customer();
        newCustomer.setCustomerName(customerName);
        newCustomer.setAddress(customerAddress);
        newCustomer.setPhone(customerPhone);
        newCustomer.setProfessional(isProfesstional);

        try {
            customerService.addCustumer(newCustomer);
            scanner.nextLine();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayCard(Customer customer) {
        final int cardWidth = 37;

        System.out.println("+" + "-".repeat(cardWidth) + "+");
        System.out.println("|" + padRight(" CUSTOMER CARD ", cardWidth) + "|");
        System.out.println("+" + "-".repeat(cardWidth) + "+");

        System.out.printf("| Name      : %-"+ (cardWidth - 14) + "s |\n", customer.getCustomerName());
        System.out.printf("| Address   : %-"+ (cardWidth - 14) + "s |\n", customer.getAddress());
        System.out.printf("| Status    : %-"+ (cardWidth - 14) + "s |\n", customer.getProfessional());

        System.out.println("+" + "-".repeat(cardWidth) + "+");
    }


    public static String padRight(String str, int length) {
        if (str.length() >= length) {
            return str;
        }
        StringBuilder sb = new StringBuilder(length);
        sb.append(str);
        for (int i = str.length(); i < length; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }

}
