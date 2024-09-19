package ma.youcode.baticuisine.views;

import ma.youcode.baticuisine.entities.*;
import ma.youcode.baticuisine.enums.ProjectStatus;
import ma.youcode.baticuisine.enums.WorkForceType;
import ma.youcode.baticuisine.services.ProjectService;
import ma.youcode.baticuisine.services.implementations.ProjectServiceImp;
import ma.youcode.baticuisine.utils.ChoiceOption;
import ma.youcode.baticuisine.utils.Validator;

import java.util.Optional;
import java.util.Scanner;

public class Menu {
    private static Scanner scanner;
    private int option;
    private static ProjectService projectService;
    private Optional<Customer> currentCustomer;
    static {
        scanner = new Scanner(System.in);
        projectService = new ProjectServiceImp();
    }

    public Menu run(Menu menu) {

        do {
            view();
            switch (option) {
                    case 1:
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

    private Optional<Customer> getCustomerForProject() {
        int option = CostumerView.view();
        switch (option) {
            case 1:
                currentCustomer = CostumerView.existCustomer();
                break;
            case 2:
                currentCustomer = CostumerView.createCustomer();
                break;
            case 3:
                System.out.println("Retour au menu précédent...");
                break;
            default:
                System.out.println("Option invalide. Veuillez réessayer.");
        }
        return currentCustomer;
    }

    public void createProject() {
        Optional<Customer> customerOptional = getCustomerForProject();
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            boolean response = Validator.validBoolean("Souhaitez-vous continuer avec ce client ? (oui/non) : ");
            if (!response) {
                System.out.println("Retour au menu principal...");
                return;
            }
            System.out.println("++++ Création d'un Nouveau Projet ++++");
            String projectName = Validator.validField("le nom de project ", null);
            Double suraface = Validator.validDouble("la surface de la cuisine (en m²)");

            Project newProject = new Project();
            newProject.setCustomer(customer);
            newProject.setProjectName(projectName);
            newProject.setSurface(suraface);

            collectComponents(newProject);

            Boolean isVat = Validator.validBoolean("Souhaitez-vous appliquer une TVA au projet ? (oui/non) : ");
            if (isVat) {
                Double vat = Validator.validDouble("le pourcentage de TVA (%)");
                for (Component component : newProject.getComponents()) {
                    component.setVat(vat);
                }
            }

            Boolean isProfit = Validator.validBoolean("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (oui/non) : ");
            if (isProfit) {
                Double profitMargin = Validator.validDouble("la marge bénéficiaire");
                newProject.setProfitMargin(profitMargin);
            }
            projectService.addProject(newProject);
        } else {
            System.out.println("client non trouve");
        }

    }

    public void collectComponents(Project project) {
        System.out.println("+++++ Ajout des Composants +++++");
        while (true) {
            Boolean addComponent = Validator.validBoolean("Souhaitez-vous ajouter un composant ? (oui/non)");
            if (addComponent) {
                boolean componentType = Validator.validQuestion("Quel type de composant voulez-vous ajouter ? (1 ---> Matériel, 2 ---> Travailleur)");
                if (componentType) {
                    String materialName = Validator.validField("le nom du matériau", null);
                    Double quantity = Validator.validDouble("la quantité de ce matériau (en litres)");
                    Double pricePerUnit = Validator.validDouble("le coût unitaire de ce matériau (€/litre)");
                    Double transportCost = Validator.validDouble("le coût de transport de ce matériau (€)");
                    Double qualityCoefficient = Validator.validCoeffcient("le coefficient de qualité du matériau (entre 1.0 et 2) : ");

                    Material material = new Material();
                    material.setComponentName(materialName);
                    material.setQuantity(quantity);
                    material.setPricePerUnit(pricePerUnit);
                    material.setTransportationCost(transportCost);
                    material.setQualityCoefficient(qualityCoefficient);
                    project.addComponent(material);
                    System.out.println("Matériau ajouté avec succès !");
                } else {
                    String workforceName = Validator.validField("le nom du travailleur", null);
                    Double hourlyRate = Validator.validDouble("le taux horaire de cette main-d'œuvre (€/h)");
                    Double workHours = Validator.validDouble("le nombre d'heures travaillées");
                    WorkForceType workForceType = Validator.choiceEnum(WorkForceType.class);
                    Double workerProductivityCoefficient = Validator.validCoeffcient("le facteur de productivité");

                    WorkForce workForce = new WorkForce();
                    workForce.setComponentName(workforceName);
                    workForce.setHourlyRate(hourlyRate);
                    workForce.setWorkHours(workHours);
                    workForce.setWorkForceType(workForceType);
                    workForce.setWorkerProductivityCoefficient(workerProductivityCoefficient);

                    project.addComponent(workForce);
                    System.out.println("Main-d'œuvre ajoutée avec succès !");
                }
            } else {
                break;
            }


        }
    }

}
