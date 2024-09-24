package ma.youcode.baticuisine.views;

import ma.youcode.baticuisine.entities.Customer;
import ma.youcode.baticuisine.services.CustomerService;
import ma.youcode.baticuisine.services.implementations.CustomerServiceImp;
import ma.youcode.baticuisine.utils.Cache;
import ma.youcode.baticuisine.utils.ChoiceOption;
import ma.youcode.baticuisine.utils.TablePrinter;
import ma.youcode.baticuisine.utils.Validator;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CustomerMenu {

    private static final int NUMBER_OPTIONS = 5;
    private static int option = 0;
    private static final Scanner scanner;
    private static final CustomerService customerService;

    static {
        scanner = new Scanner(System.in);
        customerService = new CustomerServiceImp();
    }

    public static void view() {

        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ? ");
        System.out.println(" ___________________________________");
        System.out.println("|           Menu de client          |");
        System.out.println("|___________________________________|");
        System.out.println("|                                   |");
        System.out.println("| 1. Chercher un client existant    |");
        System.out.println("| 2. Ajouter un nouveau client      |");
        System.out.println("| 3. Editer un nouveau client       |");
        System.out.println("| 4. Supprimer un client            |");
        System.out.println("| 5. Revenir au menu précédent      |");
        System.out.println("|___________________________________|");
        option = ChoiceOption.getChoice(NUMBER_OPTIONS);
    }


    public static Menu CustomerMenu(Menu menu) {
        view();

        switch (option) {
            case 1:
                existCustomer();
                break;
            case 2:
                createCustomer();
                break;
            case 3:
                handleUpdateCustomer();
                break;
            case 4:
                handleDeleteCustomer();
                break;
            case 5:
                System.out.println("Retour au menu précédent...");
                break;
            default:
                System.out.println("Option invalide. Veuillez réessayer.");
        }
        return menu;
    }


    public static void existCustomer() {
        Optional<Customer> auth = Cache.getAuthUser();
        if (!auth.isPresent()) {
            System.out.println("Vous avez choisi de chercher un client existant.");
            String customerName = Validator.validField("nom du client recherche", "[A-Za-z ]+").trim().toLowerCase();
            Optional<Customer> customer = customerService.getCustomerByName(customerName);

            customer.ifPresentOrElse(CustomerMenu::displayCard, () -> System.out.println("Client non trouve"));
            if (!customer.isPresent()) {

                Boolean response = Validator.validBoolean("est ce que vous pouvez ajouter nouveau client");
                if (response) {
                    createCustomer();
                }
            }else {
                Cache.setAuthUser(customer.get());
            }

        }
    }

    public static void createCustomer() {
        String customerName;
        while (true) {
            customerName = Validator.validField("nom du client", "[A-Za-z ]+").trim().toLowerCase();
            Optional<Customer> customer = customerService.getCustomerByName(customerName);
            if (customer.isPresent()) {
                System.out.println("le client deja exist...");
                continue;
            }
            break;
        }
        String customerAddress = Validator.validField("adresse du client ", null);
        String customerPhone = Validator.validField("telephone du client ", "^[0-9(.)-]+$");
        Boolean isProfesstional = Validator.validBoolean("si est un professional");
        Customer newCustomer = new Customer();
        newCustomer.setCustomerName(customerName);
        newCustomer.setAddress(customerAddress);
        newCustomer.setPhone(customerPhone);
        newCustomer.setProfessional(isProfesstional);
        try {
            Optional<Customer> customer = customerService.addCustomer(newCustomer);
            if (customer.isPresent()) {
                Cache.setAuthUser(customer.get());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handleUpdateCustomer() {
        List<Customer> customers = customerService.getAllCustomers();
        TablePrinter.printAllCustomer(customers);

        int i = Validator.selectIndex(customers , "Sélectionnez le nombre de client que vous souhaitez editer : ");
        Customer selectedCustomer = customers.get(i);

        String customerName = Validator.stringDefaultVal("le nom du client ", selectedCustomer.getCustomerName()).trim().toLowerCase();
        selectedCustomer.setCustomerName(customerName);

        String address = Validator.stringDefaultVal("adresse du client ", selectedCustomer.getAddress()).trim();
        selectedCustomer.setAddress(address);

        String phone = Validator.stringDefaultVal("telephone du client ", selectedCustomer.getPhone()).trim();
        selectedCustomer.setPhone(phone);

        String oldValue = selectedCustomer.getProfessional() == true ? "Oui" : "Non";
        System.out.println("Ancienne valeur est : " + oldValue );
        Boolean isProfessional = Validator.validBoolean("si est un professional");
        selectedCustomer.setProfessional(isProfessional);

        customerService.updateCustomer(selectedCustomer);

    }

    public static void handleDeleteCustomer() {
        List<Customer> customers = customerService.getAllCustomers();
        TablePrinter.printAllCustomer(customers);
        int i = Validator.selectIndex(customers , "Sélectionnez le nombre de client que vous souhaitez supprimer : ");
        Customer selectedCustomer = customers.get(i);
        customerService.deleteCustomer(selectedCustomer);

    }
    public static void displayCard(Customer customer) {
        final int cardWidth = 37;

        System.out.println("+" + "-".repeat(cardWidth) + "+");
        System.out.println("|" + padRight(" CUSTOMER CARD ", cardWidth) + "|");
        System.out.println("+" + "-".repeat(cardWidth) + "+");

        System.out.printf("| Name      : %-"+ (cardWidth - 14) + "s |\n", customer.getCustomerName());
        System.out.printf("| Address   : %-"+ (cardWidth - 14) + "s |\n", customer.getAddress());
        System.out.printf("| Professional : %-"+ (cardWidth - 17) + "s |\n", customer.getProfessional() ? "Oui" : "Non");

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
