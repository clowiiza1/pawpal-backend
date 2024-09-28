package co.za.pawpal.backend.service;

import co.za.pawpal.backend.dto.VolunteerInfoDto;
import co.za.pawpal.backend.entity.VolunteerInfo;

import java.util.List;

public interface VolunteerInfoService {
    List<VolunteerInfo> findAll();
    VolunteerInfo findById(int id);
    VolunteerInfo save(VolunteerInfoDto volunteerInfoDto);
    void deleteById(int id);
    Boolean isValid();
}
