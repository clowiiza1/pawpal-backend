package co.za.pawpal.backend.service;

import co.za.pawpal.backend.entity.VolunteerInfo;

import java.util.List;

public interface VolunteerInfoService {
    List<VolunteerInfo> findAll();
    VolunteerInfo findById(int id);
    VolunteerInfo save(VolunteerInfo volunteerInfo);
    void deleteById(int id);
}
