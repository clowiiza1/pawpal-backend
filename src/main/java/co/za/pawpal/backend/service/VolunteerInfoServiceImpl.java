package co.za.pawpal.backend.service;

import co.za.pawpal.backend.dao.UserDAO;
import co.za.pawpal.backend.dao.VolunteerInfoDAO;
import co.za.pawpal.backend.dto.VolunteerInfoDto;
import co.za.pawpal.backend.entity.AdopterSuitability;
import co.za.pawpal.backend.entity.User;
import co.za.pawpal.backend.entity.VolunteerInfo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerInfoServiceImpl implements VolunteerInfoService {

    private final VolunteerInfoDAO volunteerInfoDAO;
    private final UserDAO userDAO;


    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return userDAO.findByUsername(authentication.getName());
        }

        return null; // or throw an exception, depending on your needs
    }

    @Autowired
    public VolunteerInfoServiceImpl(VolunteerInfoDAO theVolunteerInfoDAO, UserDAO userDAO) {
        this.volunteerInfoDAO = theVolunteerInfoDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<VolunteerInfo> findAll() {
        return volunteerInfoDAO.findAll();
    }

    @Override
    public VolunteerInfo findById(int id) {
        return null;
    }


    @Override
    public VolunteerInfo findByUserId() {
        int userId = getCurrentUser().get().getId(); // Get the current user's ID
        return volunteerInfoDAO.findByUserId(userId); // Fetch volunteer info by user ID
    }

    @Transactional
    @Override
    public VolunteerInfo save(VolunteerInfoDto volunteerInfoDto) {
        VolunteerInfo volunteerInfo = new VolunteerInfo();
        volunteerInfo.setVolunteerHours(volunteerInfoDto.getVolunteerHours());
        volunteerInfo.setEmergencyContactName(volunteerInfoDto.getEmergencyContactName());
        volunteerInfo.setEmergencyContactNumber(volunteerInfoDto.getEmergencyContactNumber());
        volunteerInfo.setPreferredRoles(volunteerInfoDto.getPreferredRoles());
        volunteerInfo.setUserID(getCurrentUser().get().getId());
        return volunteerInfoDAO.save(volunteerInfo);
    }

    @Transactional
    @Override
    public VolunteerInfo update(VolunteerInfoDto volunteerInfoDto) {
        int userId = getCurrentUser().get().getId();

        // Fetch existing record by user ID
        Optional<VolunteerInfo> existingRecord = Optional.ofNullable(volunteerInfoDAO.findByUserId(userId));

        if (existingRecord.isPresent()) {
            VolunteerInfo volunteerInfo = existingRecord.get();

            // Update the existing record fields
            volunteerInfo.setPreferredRoles(volunteerInfoDto.getPreferredRoles());
            volunteerInfo.setVolunteerHours(volunteerInfoDto.getVolunteerHours());
            volunteerInfo.setEmergencyContactName(volunteerInfoDto.getEmergencyContactName());
            volunteerInfo.setEmergencyContactNumber(volunteerInfoDto.getEmergencyContactNumber());

            // Set the primary key to ensure it's an update, not an insert
            volunteerInfo.setVolunteerID(existingRecord.get().getVolunteerID());

            // Save and return the updated entity
            return volunteerInfoDAO.save(volunteerInfo);
        } else {
            throw new RuntimeException("Volunteer info not found for user ID - " + userId);
        }
    }
    @Transactional
    @Override
    public void deleteById(int id) {
        volunteerInfoDAO.deleteById(id);
    }

    public Boolean isValid() {
        Optional<User> currentUser = getCurrentUser();

        if (currentUser.isPresent()) {
            VolunteerInfo volunteerInfo = volunteerInfoDAO.findByUserId(currentUser.get().getId());

            if (volunteerInfo != null) {
                // Check if all fields are not null
                return volunteerInfo.getVolunteerHours() != null &&
                        volunteerInfo.getEmergencyContactNumber() != null &&
                        volunteerInfo.getEmergencyContactName() != null;  // Add more checks as needed
            }
        }

        return false;
    }



}
