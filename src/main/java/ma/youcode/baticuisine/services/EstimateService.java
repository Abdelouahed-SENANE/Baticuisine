package ma.youcode.baticuisine.services;

import ma.youcode.baticuisine.dto.InvoiceDTO;
import ma.youcode.baticuisine.entities.Estimate;
import ma.youcode.baticuisine.entities.Project;

import java.util.Optional;
import java.util.UUID;

public interface EstimateService {
    void addEstimate(Estimate estimate);
    InvoiceDTO generateInvoice(Project project);
    Optional<Estimate> getEstimateByProjectId(UUID id);
}
