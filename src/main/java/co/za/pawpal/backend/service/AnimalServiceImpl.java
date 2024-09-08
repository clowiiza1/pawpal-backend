package co.za.pawpal.backend.service;

import co.za.pawpal.backend.dao.AnimalDAO;
import co.za.pawpal.backend.entity.Animal;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {

    private AnimalDAO animalDAO;

    @Autowired
    public AnimalServiceImpl(AnimalDAO theAnimalDAO) {
        this.animalDAO = theAnimalDAO;
    }

    @Override
    public List<Animal> findAll() {
        return animalDAO.findAll();
    }

    @Override
    public Animal findById(int id) {
        Optional<Animal> result = Optional.ofNullable(animalDAO.findById(id));
        Animal theAnimal = null;

        if (result.isPresent()) {
            theAnimal = result.get();
        } else {
            throw new RuntimeException("Did not find animal with id: " + id);
        }

        return theAnimal;
    }

    @Transactional
    @Override
    public Animal save(Animal animal) {
        return animalDAO.save(animal);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        animalDAO.deleteById(id);
    }

    @Override
    public Optional<Animal> findByName(String name) {
        return Optional.ofNullable(animalDAO.findByName(name));
    }
}
