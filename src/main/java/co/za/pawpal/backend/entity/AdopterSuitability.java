package co.za.pawpal.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "adopter_suitability")
public class AdopterSuitability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SuitabilityID")
    private int suitabilityId;

    @Column(name = "UserID")
    private int userId;

    @Column(name = "No_Animals")
    private int noOfAnimals;

    @Column(name = "House_Type")
    private int houseType;

    @Column(name = "Adopter_Suitability")
    private String adopterSuitability;

    // Getters and Setters
    public int getSuitabilityId() {
        return suitabilityId;
    }

    public void setSuitabilityId(int suitabilityId) {
        this.suitabilityId = suitabilityId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNoOfAnimals() {
        return noOfAnimals;
    }

    public void setNoOfAnimals(int noOfAnimals) {
        this.noOfAnimals = noOfAnimals;
    }

    public int getHouseType() {
        return houseType;
    }

    public void setHouseType(int houseType) {
        this.houseType = houseType;
    }

    public String getAdopterSuitability() {
        return adopterSuitability;
    }

    public void setAdopterSuitability(String adopterSuitability) {
        this.adopterSuitability = adopterSuitability;
    }

    @Override
    public String toString() {
        return "AdopterSuitability{" +
                "suitabilityId=" + suitabilityId +
                ", userId=" + userId +
                ", noOfAnimals=" + noOfAnimals +
                ", houseType=" + houseType +
                ", adopterSuitability='" + adopterSuitability + '\'' +
                '}';
    }
}