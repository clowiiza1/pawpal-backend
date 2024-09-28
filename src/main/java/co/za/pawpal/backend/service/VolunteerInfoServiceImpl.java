package co.za.pawpal.backend.service;

import co.za.pawpal.backend.dao.UserDAO;
import co.za.pawpal.backend.dao.VolunteerInfoDAO;
import co.za.pawpal.backend.dto.VolunteerInfoDto;
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
        return volunteerInfoDAO.findById(id);
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
