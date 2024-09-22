package ma.youcode.baticuisine.utils;

import ma.youcode.baticuisine.dto.InvoiceDTO;
import ma.youcode.baticuisine.dto.MaterialDTO;
import ma.youcode.baticuisine.dto.WorkForceDTO;

public class InvoicePrinter {

    public static void print(InvoiceDTO invoice) {
        // Table width
        int tableWidth = 117;

        // Printing Header
        printLine(tableWidth);
        printCentered("Nom du projet: " + invoice.getProjectName(), tableWidth);
        printCentered("Client: " + invoice.getClientName(), tableWidth);
        printCentered("Adresse: " + invoice.getAddress(), tableWidth);
        printLine(tableWidth);
        printCentered("Détail de Devis", tableWidth);
        printLine(tableWidth);

        System.out.printf("| %-38s | %-10s | %-15s | %-10s | %-10s | %-15s |\n",
                "Type", "Quantité", "Prix Unit.", "Qualité", "Transport" , "Total HT");
        printLine(tableWidth);

        for (MaterialDTO material : invoice.getMaterials()) {
            System.out.printf("| %-38s | %-10s | %-15s | %-10s | %-10s | %-15s |\n",
                    material.getName() + " (Material)",
                    material.getQuantity() + " m²",
                    String.format("%.2f €/m²", material.getPriceUnit()),
                    material.getQuality() ,
                    String.format("%.2f €", material.getTransportCost()),
                    String.format("%.2f €", material.getAmountHT())
                    );

        }

        for (WorkForceDTO workForce : invoice.getWorkForces()) {
            System.out.printf("| %-38s | %-10s | %-15s | %-10s | %-10s | %-15s |\n",
                    workForce.getName() + " (Main d'oeuvre)",
                    workForce.getWorkHours() + " h",
                    String.format("%.2f €/h", workForce.getHourlyRate()),
                    workForce.getWorkerProductivityCoefficient(),
                    String.format("-", "-"),
                    String.format("%.2f €", workForce.getAmountHT()));
        }
        printLine(tableWidth);
        Double profitRate = invoice.getProfitRate() > 0 ? Math.round((invoice.getProfitRate() - 1 ) * 100) : 0.00;
        System.out.printf("| %-50s %62s |\n", "Montant (HT):", String.format("%.2f €", invoice.getAmountHT()));
        System.out.printf("| %-50s %62s |\n", "Remise ("  + String.format("%.0f" , invoice.getDiscountRate()) +"%):", String.format("%.2f €", invoice.getDiscountValue()));
        System.out.printf("| %-50s %62s |\n", "Montant Net (HT):", String.format("%.2f €", invoice.getNetAmout()));
        System.out.printf("| %-50s %62s |\n", "Taux de TVA (" + String.format("%.0f" , invoice.getTax()) + "%):", String.format("%.2f €", invoice.getAmountWithTax()));
        System.out.printf("| %-50s %62s |\n", "Montant (TTC):", String.format("%.2f €", invoice.getAmountTTC()));
        System.out.printf("| %-50s %62s |\n", "Marge bénéficiaire (" + String.format("%.0f" , profitRate) +"%) :", String.format("%.2f €", invoice.getProfitMargin()));
        System.out.printf("| %-50s %62s |\n", "Montant total du projet:", String.format("%.2f €", invoice.getAmountWithProfit()));
        printLine(tableWidth);
    }

    // Helper methods to print lines and centered text
    private static void printLine(int width) {
        System.out.println("+" + "-".repeat(width - 2) + "+");
    }

    private static void printCentered(String text, int width) {
        int padding = (width - text.length()) / 2;
        System.out.printf("|%" + padding + "s%s%" + (width - padding - text.length() - 2) + "s|\n", "", text, "");
    }
}
