package co.za.pawpal.backend.service;

import co.za.pawpal.backend.dao.AdopterSuitabilityDAO;
import co.za.pawpal.backend.dao.UserDAO;
import co.za.pawpal.backend.dto.AdopterSuitabilityDto;
import co.za.pawpal.backend.entity.AdopterSuitability;
import co.za.pawpal.backend.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdopterSuitabilityServiceImpl implements AdopterSuitabilityService {

    private final AdopterSuitabilityDAO adopterSuitabilityDAO;
    private final UserDAO userDAO;


    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return userDAO.findByUsername(authentication.getName());
        }

        return null; // or throw an exception, depending on your needs
    }

    @Autowired
    public AdopterSuitabilityServiceImpl(AdopterSuitabilityDAO adopterSuitabilityDAO, UserDAO userDAO) {
        this.adopterSuitabilityDAO = adopterSuitabilityDAO;
        this.userDAO = userDAO;
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
    public AdopterSuitability save(AdopterSuitabilityDto adopterSuitabilityDto) {
        int id = getCurrentUser().get().getId();
        AdopterSuitability adopterSuitability = new AdopterSuitability();
        adopterSuitability.setHouseType(adopterSuitabilityDto.getHouseType());
        adopterSuitability.setNoOfAnimals(adopterSuitabilityDto.getNoAnimals());
        adopterSuitability.setUserId(id);
        return adopterSuitabilityDAO.save(adopterSuitability);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        adopterSuitabilityDAO.deleteById(id);
    }

    @Override
    public Optional<AdopterSuitability> findByUserId(int userId) {
        return adopterSuitabilityDAO.findByUserId(userId);
    }

    @Override
    public AdopterSuitability findByUsername(String username) {
        Optional<User> user = userDAO.findByUsername(username);
        if (user.isPresent()) {
            return adopterSuitabilityDAO.findByUserId(user.get().getId()).orElse(null);
        }
        return null;
    }



    public boolean existsByUsername(String username) {
        // Find user by username
        Optional<User> user = userDAO.findByUsername(username);
        if (user.isPresent()) {
            // Check if AdopterSuitability exists for this user
            return adopterSuitabilityDAO.existsByUserId(user.get().getId());
        }
        // User not found or no AdopterSuitability record
        return false;
    }
}
