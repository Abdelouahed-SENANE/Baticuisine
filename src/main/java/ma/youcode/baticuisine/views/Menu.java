package ma.youcode.baticuisine.views;

import ma.youcode.baticuisine.dto.InvoiceDTO;
import ma.youcode.baticuisine.entities.*;
import ma.youcode.baticuisine.enums.ComponentType;
import ma.youcode.baticuisine.enums.WorkForceType;
import ma.youcode.baticuisine.services.EstimateService;
import ma.youcode.baticuisine.services.ProjectService;
import ma.youcode.baticuisine.services.implementations.EstimateServiceImp;
import ma.youcode.baticuisine.services.implementations.ProjectServiceImp;
import ma.youcode.baticuisine.utils.ChoiceOption;
import ma.youcode.baticuisine.utils.InvoicePrinter;
import ma.youcode.baticuisine.utils.TablePrinter;
import ma.youcode.baticuisine.utils.Validator;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private static Scanner scanner;
    private int option;
    private static ProjectService projectService;
    private  static EstimateService estimateService;
    private Optional<Customer> currentCustomer;
    static {
        scanner = new Scanner(System.in);
        projectService = new ProjectServiceImp();
        estimateService = new EstimateServiceImp();
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
        option = ChoiceOption.getChoice(4);
    }
    public Menu run(Menu menu) {

        do {
            view();
            switch (option) {
                    case 1:
                        createProject();
                        break;
                    case 2:
                        displayProjects();
                        break;
                    case 3:
                        calculateProjectCost();
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

            if (customer.getProfessional()) {
                System.out.println("Le client est professionnel, application de la remise.");
                Double discount = Validator.validDouble("le taux de la remise");
                newProject.setDiscount(discount);
            }else {
                newProject.setDiscount(0.00);
            }

            System.out.println("+++++ Ajout des matériaux +++++");
            collectMaterials(newProject);
            collectWorkforce(newProject);
            System.out.println("+++++ Calcul du coût total +++++");

            Boolean isVat = Validator.validBoolean("Souhaitez-vous appliquer une TVA au projet ? (oui/non) : ");
            if (isVat) {
                Double vat = Validator.validDouble("le pourcentage de TVA (%)");
                for (Component component : newProject.getComponents()) {
                    component.setVat(vat);
                }
            }else{
                for (Component component : newProject.getComponents()) {
                    component.setVat(0.00);
                }
            }

            Boolean isProfit = Validator.validBoolean("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (oui/non) : ");
            if (isProfit) {
                Double profitMargin = Validator.validCoeffcient("la marge bénéficiaire coefficient");
                newProject.setProfitMargin(profitMargin);
            }else {
                newProject.setProfitMargin(0.00);
            }

            System.out.println("Calcul du coût en cours...");
            InvoiceDTO invoice = estimateService.generateInvoice(newProject);
            InvoicePrinter.print(invoice);

            Boolean saveDevis = Validator.validBoolean("Souhaitez-vous enregistrer le devis ? (oui/non)");
            if (saveDevis) {
                Estimate estimate = new Estimate();
                LocalDate issueDate = Validator.validDate("la date d'émission");
                LocalDate validateDate = Validator.validDate("la date de validité");
                if (issueDate.isAfter(validateDate)) {
                    System.out.println("Erreur : la date de validation doit être supérieure à la date d'émission.");
                    validateDate = Validator.validDate("la date de validité");
                }

                estimate.setIssueAt(issueDate);

                estimate.setValidateAt(validateDate);
                Boolean accepted = Validator.validBoolean("Le client a-t-il accepté le prix du devis ? (oui/non) : ");
                if (accepted) {
                    estimate.setAccepted(true);
                }else {
                    estimate.setAccepted(false);
                }

                UUID projectId =  projectService.addProject(newProject);
                newProject.setProjectId(projectId);
                estimate.setProject(newProject);
                this.estimateService.addEstimate(estimate);
                System.out.println("Devis enregistré avec succès !");
            }else {
                System.out.println("Échec de l'enregistrement du devis !");
            }
        } else {
            System.out.println("client non trouve");
        }

    }

    public void collectWorkforce(Project project) {
        while (true) {
            String workforceName = Validator.validField("le nom de la main-d'œuvre", null);
            Double hourlyRate = Validator.validDouble("le taux horaire de cette main-d'œuvre (€/h)");
            Double workHours = Validator.validDouble("le nombre d'heures travaillées");
            WorkForceType workForceType = Validator.choiceEnum(WorkForceType.class);
            Double workerProductivityCoefficient = Validator.validCoeffcient("le facteur de productivité");
            WorkForce workForce = new WorkForce();
            workForce.setComponentName(workforceName);
            workForce.setHourlyRate(hourlyRate);
            workForce.setComponentType(ComponentType.WorkForce);
            workForce.setWorkHours(workHours);
            workForce.setWorkForceType(workForceType);
            workForce.setWorkerProductivityCoefficient(workerProductivityCoefficient);
            project.addComponent(workForce);
            System.out.println("Main-d'œuvre ajoutée avec succès !");
            Boolean addComponent = Validator.validBoolean("Souhaitez-vous ajouter autre main-d'œuvre ? (oui/non)");
            if (addComponent) {
                System.out.println("Continue ...");
            } else {
                break;
            }
        }
    }

    public void collectMaterials(Project project) {
        while (true) {
            String materialName = Validator.validField("le nom du matériau", null);
            Double quantity = Validator.validDouble("la quantité de ce matériau (en litres)");
            Double pricePerUnit = Validator.validDouble("le coût unitaire de ce matériau (€/litre)");
            Double transportCost = Validator.validDouble("le coût de transport de ce matériau (€)");
            Double qualityCoefficient = Validator.validCoeffcient("le coefficient de qualité du matériau (entre 1.0 et 2) : ");
            Material material = new Material();
            material.setComponentName(materialName);
            material.setQuantity(quantity);
            material.setPricePerUnit(pricePerUnit);
            material.setComponentType(ComponentType.Material);
            material.setTransportationCost(transportCost);
            material.setQualityCoefficient(qualityCoefficient);
            project.addComponent(material);
            System.out.println("Matériau ajouté avec succès !");
            Boolean response = Validator.validBoolean("Souhaitez-vous ajouter autre Matériel ? (oui/non)");
            if (response) {
                System.out.println("Continue ...");
            } else {
                break;
            }

        }
    }

    public void displayProjects() {

    }

    public void calculateProjectCost() {
        List<Project> projects = this.projectService.getAllProjects();
        TablePrinter.printProjects(projects);

        int i = Validator.selectIndex(projects);

        System.out.println("Calcul du coût en cours...");
        InvoiceDTO invoice = estimateService.generateInvoice(projects.get(i));
        InvoicePrinter.print(invoice);

    }

}
