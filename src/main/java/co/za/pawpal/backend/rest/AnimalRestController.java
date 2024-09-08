package co.za.pawpal.backend.rest;

import co.za.pawpal.backend.entity.Animal;
import co.za.pawpal.backend.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnimalRestController {

    private AnimalService animalService;

    @Autowired
    public AnimalRestController(AnimalService theAnimalService) {
        animalService = theAnimalService;
    }

    @GetMapping("/animals")
    public List<Animal> findAll() {
        try {
            List<Animal> animalList = animalService.findAll();
            return animalList;
        }
        catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/animals/{animalId}")
    public Animal getAnimal(@PathVariable int animalId) {
        Animal theAnimal = animalService.findById(animalId);

        if (theAnimal == null) {
            throw new RuntimeException("Animal id not found - " + animalId);
        }
        return theAnimal;
    }

    @PostMapping("/animals")
    public Animal addAnimal(@RequestBody Animal theAnimal) {
        // Set ID to 0 to force a save of a new item instead of an update
//        theAnimal.setId(0);
        Animal dbAnimal = animalService.save(theAnimal);
        return dbAnimal;
    }

    @PutMapping("/animals")
    public Animal updateAnimal(@RequestBody Animal theAnimal) {
        Animal dbAnimal = animalService.save(theAnimal);
        return dbAnimal;
    }

    @DeleteMapping("/animals/{animalId}")
    public String deleteAnimal(@PathVariable int animalId) {
        Animal theAnimal = animalService.findById(animalId);

        if (theAnimal == null) {
            throw new RuntimeException("Animal id not found - " + animalId);
        }

        animalService.deleteById(animalId);
        return "Deleted animal id - " + animalId;
    }
}

