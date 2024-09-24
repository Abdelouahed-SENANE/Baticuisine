package ma.youcode.baticuisine.views;

import ma.youcode.baticuisine.dto.InvoiceDTO;
import ma.youcode.baticuisine.entities.*;
import ma.youcode.baticuisine.enums.WorkForceType;
import ma.youcode.baticuisine.services.EstimateService;
import ma.youcode.baticuisine.services.ProjectService;
import ma.youcode.baticuisine.services.implementations.EstimateServiceImp;
import ma.youcode.baticuisine.services.implementations.ProjectServiceImp;
import ma.youcode.baticuisine.utils.*;

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
        System.out.println("| 4. Éditer un projet               |");
        System.out.println("| 5. Supprimer un projet            |");
        System.out.println("| 6. Accéder au menu du client      |");
        System.out.println("| 7. Quitter                        |");
        System.out.println("|___________________________________|");
        option = ChoiceOption.getChoice(7);
    }
    public Menu run(Menu menu) {

        do {
            view();
            switch (option) {
                    case 1:
                        CustomerMenu.existCustomer();
                        createProject();
                        break;
                    case 2:
                        displayProjects();
                        break;
                    case 3:
                        calculateProjectCost();
                    break;
                case 4:
                    handleUpdateProject();
                    break;
                case 5:
                    handleDeleteProject();
                    break;
                case 6:
                    CustomerMenu.CustomerMenu(menu);
                    break;
                case 7:
                         System.out.println("Merci d'avoir utilisé l'application. À bientôt !");
                         System.exit(0);
                        break;
                default:
                    System.out.print("Option invalide. Veuillez réessayer : ");
            }
        }while (option != 7);

        return menu;

    }

    public void handleUpdateProject() {
        List<Project> projects = projectService.getAllProjects();
        TablePrinter.printProjects(projects);
        int index = Validator.selectIndex(projects , "Sélectionnez le nombre de projet que vous souhaitez editer : ");
        UUID projectId = projects.get(index).getProjectId();
        Optional<Project> optionalProject = projectService.getProjectById(projectId);

        if (optionalProject.isPresent()) {
            Project getProject = optionalProject.get();
            Project editProject = createEditProject(getProject);
            projectService.updateProject(editProject);
            System.out.println("Projet mis à jour avec succès !");
        } else {
            System.out.println("Projet n'existe pas !");
        }
    }


    private Project createEditProject(Project getProject) {
        Project editProject = new Project();

        editProject.setProjectId(getProject.getProjectId());

        System.out.println("++++ Modification du Projet Existant ++++");

        String projectName = Validator.stringDefaultVal("le nom de projet", getProject.getProjectName());
        editProject.setProjectName(projectName);

        Double profitMargin = Validator.doubleDefaultVal("la marge bénéficiaire", getProject.getProfitMargin());
        editProject.setProfitMargin(profitMargin);

        if (getProject.getCustomer().getProfessional()) {
            Double discount = Validator.doubleDefaultVal("la remise", getProject.getDiscount());
            editProject.setDiscount(discount);
        } else {
            editProject.setDiscount(0.00);
        }

        return editProject;
    }





    public void handleDeleteProject() {
        List<Project> projects = this.projectService.getAllProjects();
        TablePrinter.printProjects(projects);
        int i = Validator.selectIndex(projects , "Sélectionnez le nombre de projet que vous souhaitez supprimer : ");

        projectService.deleteProject(projects.get(i).getProjectId());
    }

    public void createProject() {
        Optional<Customer> customerOptional = Cache.getAuthUser();
        String costumerName = customerOptional.isPresent() ? customerOptional.get().getCustomerName() : "";
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();

            boolean response = Validator.validBoolean("Souhaitez-vous continuer avec ce client "+ costumerName.toUpperCase() + " (oui/non)");
            if (!response) {
                Cache.setAuthUser(null);
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

            UUID projectId = projectService.addProject(newProject);
            if (projectId != null) {
                newProject.setProjectId(projectId);
                createEstimate(newProject);
            }

        } else {
            System.out.println("client non trouve");
        }

    }

    public void createEstimate(Project project) {
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
            } else {
                estimate.setAccepted(false);
            }
            estimate.setProject(project);
            this.estimateService.addEstimate(estimate);
            System.out.println("Devis enregistré avec succès !");
        } else {
            System.out.println("l'enregistrement du devis a ete annule !");
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
        List<Project> projects = this.projectService.getAllProjects();
        TablePrinter.printProjects(projects);
    }

    public void calculateProjectCost() {
        List<Project> projects = this.projectService.getAllProjects();
        TablePrinter.printProjects(projects);

        int i = Validator.selectIndex(projects , "Sélectionnez le nombre de projet que vous souhaitez calculer : ");
        UUID projectId = projects.get(i).getProjectId();
        Optional<Estimate> existCustomer = estimateService.getEstimateByProjectId(projectId);
        if (existCustomer.isPresent()) {
            System.out.println("Calcul du coût en cours...");
            InvoiceDTO invoice = estimateService.generateInvoice(projects.get(i));
            InvoicePrinter.print(invoice);
        } else {
            String yellow = "\u001B[33m";
            String reset = "\u001B[0m";
            System.out.println("\n" + yellow + "      Alerte ! Ce projet n'a pas encore de devis.\n" + reset);
            createEstimate(projects.get(i));
        }
    }

}
