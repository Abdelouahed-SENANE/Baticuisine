package ma.youcode.baticuisine.services.implementations;

import ma.youcode.baticuisine.dto.InvoiceDTO;
import ma.youcode.baticuisine.entities.Estimate;
import ma.youcode.baticuisine.entities.Project;
import ma.youcode.baticuisine.repositories.EstimateRepository;
import ma.youcode.baticuisine.repositories.implementations.EstimateRepositoryImp;
import ma.youcode.baticuisine.services.EstimateService;
import ma.youcode.baticuisine.services.MaterialService;
import ma.youcode.baticuisine.services.WorkForceService;

public class EstimateServiceImp implements EstimateService {
    private final WorkForceService workForceService;
    private final MaterialService materialService;
    private final EstimateRepository estimateRepository;

    public EstimateServiceImp() {
        materialService = new MaterialServiceImp();
        workForceService = new WorkForceServiceImp();
        estimateRepository = new EstimateRepositoryImp();
    }

    @Override
    public void addEstimate(Estimate estimate) {
        this.estimateRepository.save(estimate);
    }

    @Override
    public InvoiceDTO generateInvoice(Project project) {

        Double totalAmountWorkforces = workForceService.calculateCostForWorkForceHT(project);
        Double totalAmountMaterials = materialService.caculateCostMaterialsHT(project);

        Double amountHT = totalAmountWorkforces + totalAmountMaterials;
        Double discountValue = amountHT * project.getDiscount() / 100;

        Double netAmount = amountHT - discountValue;
        Double tax = project.getComponents().get(0).getVat();
        Double amountTax = netAmount * (tax / 100);
        Double amountTTC = netAmount + (netAmount * (tax / 100));
        Double finalCost = project.getProfitMargin() > 0 ? amountTTC * (project.getProfitMargin()) : amountTTC;

        Double profitMargin = finalCost - amountTTC;

        return new InvoiceDTO(
                project,
                amountHT,
                amountTTC,
                finalCost,
                profitMargin,
                amountTax,
                discountValue,
                netAmount
        );

    }
}
