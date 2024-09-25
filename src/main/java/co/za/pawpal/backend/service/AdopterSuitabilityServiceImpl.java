package co.za.pawpal.backend.service;

import co.za.pawpal.backend.dao.AdopterSuitabilityDAO;
import co.za.pawpal.backend.entity.AdopterSuitability;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdopterSuitabilityServiceImpl implements AdopterSuitabilityService {

    private final AdopterSuitabilityDAO adopterSuitabilityDAO;

    @Autowired
    public AdopterSuitabilityServiceImpl(AdopterSuitabilityDAO adopterSuitabilityDAO) {
        this.adopterSuitabilityDAO = adopterSuitabilityDAO;
    }

    @Override
    public List<AdopterSuitability> findAll() {
        return adopterSuitabilityDAO.findAll();
    }

    @Override
    public AdopterSuitability findById(int id) {
        return adopterSuitabilityDAO.findById(id);
    }

    @Transactional
    @Override
    public AdopterSuitability save(AdopterSuitability adopterSuitability) {
        return adopterSuitabilityDAO.save(adopterSuitability);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        adopterSuitabilityDAO.deleteById(id);
    }
}
