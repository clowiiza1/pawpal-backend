package co.za.pawpal.backend.dao;

import co.za.pawpal.backend.entity.AdopterSuitability;
import java.util.List;

public interface AdopterSuitabilityDAO {
    List<AdopterSuitability> findAll();

    AdopterSuitability findById(int id);

    AdopterSuitability save(AdopterSuitability adopterSuitability);

    void deleteById(int id);
}
