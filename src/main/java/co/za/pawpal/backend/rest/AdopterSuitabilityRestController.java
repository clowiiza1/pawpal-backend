package co.za.pawpal.backend.rest;

import co.za.pawpal.backend.dto.AdopterSuitabilityDto;
import co.za.pawpal.backend.entity.AdopterSuitability;
import co.za.pawpal.backend.service.AdopterSuitabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdopterSuitabilityRestController {

    private final AdopterSuitabilityService adopterSuitabilityService;

    @Autowired
    public AdopterSuitabilityRestController(AdopterSuitabilityService theAdopterSuitabilityService) {
        this.adopterSuitabilityService = theAdopterSuitabilityService;
    }

    @GetMapping("/adopter-suitability")
    public List<AdopterSuitability> findAll() {
        return adopterSuitabilityService.findAll();
    }

    @GetMapping("/adopter-suitability/{suitabilityId}")
    public AdopterSuitability getAdopterSuitability(@PathVariable int suitabilityId) {
        AdopterSuitability adopterSuitability = adopterSuitabilityService.findById(suitabilityId);
        if (adopterSuitability == null) {
            throw new RuntimeException("SuitabilityID not found - " + suitabilityId);
        }
        return adopterSuitability;
    }

    @GetMapping("/adopter-suitability/user/{username}")
    public AdopterSuitability getAdopterSuitabilityByUsername(@PathVariable String username) {
        AdopterSuitability adopterSuitability = adopterSuitabilityService.findByUsername(username);
        if (adopterSuitability == null) {
            throw new RuntimeException("No AdopterSuitability record found for username - " + username);
        }
        return adopterSuitability;
    }

    // Updated endpoint to check if AdopterSuitability exists by username
    @GetMapping("/adopter-suitability/user/{username}/exists")
    public boolean checkIfAdopterSuitabilityExists(@PathVariable String username) {
        return adopterSuitabilityService.existsByUsername(username);
    }

    @PostMapping("/adopter-suitability")
    public AdopterSuitability addAdopterSuitability(@RequestBody AdopterSuitabilityDto adopterSuitability) {
        return adopterSuitabilityService.save(adopterSuitability);
    }

    @PutMapping("/adopter-suitability")
    public AdopterSuitability updateAdopterSuitability(@RequestBody AdopterSuitabilityDto adopterSuitability) {
        return adopterSuitabilityService.update(adopterSuitability);
    }

    @DeleteMapping("/adopter-suitability/{suitabilityId}")
    public String deleteAdopterSuitability(@PathVariable int suitabilityId) {
        AdopterSuitability adopterSuitability = adopterSuitabilityService.findById(suitabilityId);
        if (adopterSuitability == null) {
            throw new RuntimeException("SuitabilityID not found - " + suitabilityId);
        }

        adopterSuitabilityService.deleteById(suitabilityId);
        return "Deleted adopter suitability id - " + suitabilityId;
    }
}
