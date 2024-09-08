package co.za.pawpal.backend.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnimalID")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Species")
    private String species;

    @Column(name = "Breed")
    private String breed;

    @Column(name = "Age")
    private int age;

    @Column(name = "Arrival")
    @Temporal(TemporalType.DATE)
    private Date arrivalDate;

    @Column(name = "Status")
    private String status;

    @Column(name = "Gender")
    private char gender;

    @Column(name = "Weight")
    private double weight;

    @Column(name = "Vaccinated")
    private boolean isVaccinated;

    @Column(name = "Sterile")
    private boolean isSterile;

    @Column(name = "Description")
    private String description;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    public boolean isSterile() {
        return isSterile;
    }

    public void setSterile(boolean sterile) {
        isSterile = sterile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", arrivalDate=" + arrivalDate +
                ", status='" + status + '\'' +
                ", gender=" + gender +
                ", weight=" + weight +
                ", isVaccinated=" + isVaccinated +
                ", isSterile=" + isSterile +
                ", description='" + description + '\'' +
                '}';
    }
}
