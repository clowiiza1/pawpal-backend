package co.za.pawpal.backend.service;

import co.za.pawpal.backend.dto.AdopterSuitabilityDto;
import co.za.pawpal.backend.entity.AdopterSuitability;
import java.util.List;
import java.util.Optional;

public interface AdopterSuitabilityService {
    List<AdopterSuitability> findAll();

    AdopterSuitability findById(int id);

    AdopterSuitability save(AdopterSuitabilityDto adopterSuitability);

    void deleteById(int id);

    Optional<AdopterSuitability> findByUserId(int userId);

    AdopterSuitability findByUsername(String username);

    boolean existsByUsername(String username);
}
