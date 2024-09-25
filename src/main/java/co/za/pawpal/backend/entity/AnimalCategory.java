package co.za.pawpal.backend.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "animal_categories")
public class AnimalCategory {

    @Id
    @Column(name = "CategoryID")
    private int categoryID;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
    private List<Animal> animals = new ArrayList<>();

    public AnimalCategory() {
    }

    public int getId() {
        return categoryID;
    }

    public void setId(int id) {
        this.categoryID = id;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public String toString() {
        return "AnimalCategory{" +
                "id=" + categoryID +
                '}';
    }
}
