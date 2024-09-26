package co.za.pawpal.backend.dao;

import co.za.pawpal.backend.entity.AdopterSuitability;
import java.util.List;
import java.util.Optional;

public interface AdopterSuitabilityDAO {
    List<AdopterSuitability> findAll();

    AdopterSuitability findById(int id);

    AdopterSuitability save(AdopterSuitability adopterSuitability);

    void deleteById(int id);

    Optional<AdopterSuitability> findByUserId(int userId);

    boolean existsByUserId(int userId);



}
