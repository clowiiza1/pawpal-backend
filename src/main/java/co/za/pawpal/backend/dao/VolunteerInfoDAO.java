package co.za.pawpal.backend.dao;

import co.za.pawpal.backend.entity.VolunteerInfo;

import java.util.List;

public interface VolunteerInfoDAO {
    List<VolunteerInfo> findAll();
    VolunteerInfo findById(int id);
    VolunteerInfo save(VolunteerInfo volunteerInfo);
    void deleteById(int id);
    VolunteerInfo findByUserId(int userId);
}
