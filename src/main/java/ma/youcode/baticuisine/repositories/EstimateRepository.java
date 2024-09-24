package ma.youcode.baticuisine.repositories;

import ma.youcode.baticuisine.entities.Estimate;

import java.util.Optional;
import java.util.UUID;

public interface EstimateRepository {
    void save(Estimate estimate);
    Optional<Estimate> findByProjectId(UUID id);
}
