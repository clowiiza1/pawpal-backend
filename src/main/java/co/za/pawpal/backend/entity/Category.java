package co.za.pawpal.backend.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private int id;

    @Column(name = "Category_Name")
    private String name;

    @Column(name = "Animal_Type")
    private String animalType;

    @ManyToMany(mappedBy = "categories")
    private List<Animal> animals = new ArrayList<>();


    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Animal> getAnimals() {
        return animals;
    }
}
