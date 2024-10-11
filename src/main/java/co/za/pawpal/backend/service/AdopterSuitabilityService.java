package co.za.pawpal.backend.service;

import co.za.pawpal.backend.dto.AdopterSuitabilityDto;
import co.za.pawpal.backend.entity.AdopterSuitability;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface AdopterSuitabilityService {
    List<AdopterSuitability> findAll();

    AdopterSuitability findById(int id);

    AdopterSuitability save(AdopterSuitabilityDto adopterSuitability);

    AdopterSuitability update(AdopterSuitabilityDto adopterSuitabilityDto);

    void deleteById(int id);

    Optional<AdopterSuitability> findByUserId(int userId);

    AdopterSuitability findByUsername(String username);

    boolean existsByUsername(String username);
}
