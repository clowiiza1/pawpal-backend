package co.za.pawpal.backend.rest;

import co.za.pawpal.backend.entity.VolunteerInfo;
import co.za.pawpal.backend.service.VolunteerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VolunteerInfoRestController {

    private final VolunteerInfoService volunteerInfoService;

    @Autowired
    public VolunteerInfoRestController(VolunteerInfoService theVolunteerInfoService) {
        this.volunteerInfoService = theVolunteerInfoService;
    }

    @GetMapping("/volunteer-info")
    public List<VolunteerInfo> findAll() {
        return volunteerInfoService.findAll();
    }

    @GetMapping("/volunteer-info/{volunteerId}")
    public VolunteerInfo getVolunteerInfo(@PathVariable int volunteerId) {
        VolunteerInfo volunteerInfo = volunteerInfoService.findById(volunteerId);
        if (volunteerInfo == null) {
            throw new RuntimeException("Volunteer ID not found - " + volunteerId);
        }
        return volunteerInfo;
    }

    @PostMapping("/volunteer-info")
    public VolunteerInfo addVolunteerInfo(@RequestBody VolunteerInfo volunteerInfo) {
        return volunteerInfoService.save(volunteerInfo);
    }

    @PutMapping("/volunteer-info")
    public VolunteerInfo updateVolunteerInfo(@RequestBody VolunteerInfo volunteerInfo) {
        return volunteerInfoService.save(volunteerInfo);
    }

    @DeleteMapping("/volunteer-info/{volunteerId}")
    public String deleteVolunteerInfo(@PathVariable int volunteerId) {
        VolunteerInfo volunteerInfo = volunteerInfoService.findById(volunteerId);
        if (volunteerInfo == null) {
            throw new RuntimeException("Volunteer ID not found - " + volunteerId);
        }

        volunteerInfoService.deleteById(volunteerId);
        return "Deleted volunteer id - " + volunteerId;
    }
}
