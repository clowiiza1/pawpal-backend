package co.za.pawpal.backend.service;

import co.za.pawpal.backend.dao.VolunteerInfoDAO;
import co.za.pawpal.backend.entity.VolunteerInfo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerInfoServiceImpl implements VolunteerInfoService {

    private final VolunteerInfoDAO volunteerInfoDAO;

    @Autowired
    public VolunteerInfoServiceImpl(VolunteerInfoDAO theVolunteerInfoDAO) {
        this.volunteerInfoDAO = theVolunteerInfoDAO;
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
    public VolunteerInfo save(VolunteerInfo volunteerInfo) {
        return volunteerInfoDAO.save(volunteerInfo);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        volunteerInfoDAO.deleteById(id);
    }
}
