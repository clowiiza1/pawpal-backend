package co.za.pawpal.backend.dao;

import co.za.pawpal.backend.entity.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalDAO {
    List<Animal> findAll();

    Animal findById(int id);

    Animal save(Animal animal);

    void deleteById(int id);

    Animal findByName(String name);


}
