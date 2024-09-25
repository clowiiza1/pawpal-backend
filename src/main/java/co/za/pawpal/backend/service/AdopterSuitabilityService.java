package co.za.pawpal.backend.service;

import co.za.pawpal.backend.entity.AdopterSuitability;
import java.util.List;

public interface AdopterSuitabilityService {
    List<AdopterSuitability> findAll();

    AdopterSuitability findById(int id);

    AdopterSuitability save(AdopterSuitability adopterSuitability);

    void deleteById(int id);
}
