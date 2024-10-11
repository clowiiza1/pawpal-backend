package co.za.pawpal.backend.service;

import co.za.pawpal.backend.dto.VolunteerInfoDto;
import co.za.pawpal.backend.entity.VolunteerInfo;
import jakarta.transaction.Transactional;

import java.util.List;

public interface VolunteerInfoService {
    List<VolunteerInfo> findAll();
    VolunteerInfo findById(int id);

    VolunteerInfo findByUserId();

    VolunteerInfo save(VolunteerInfoDto volunteerInfoDto);

    @Transactional
    VolunteerInfo update(VolunteerInfoDto volunteerInfoDto);

    void deleteById(int id);
    Boolean isValid();
}
