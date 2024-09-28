package co.za.pawpal.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "volunteer_info")
public class VolunteerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VolunteerID")
    private int volunteerID;

    @Column(name = "UserID")
    private int userID;

    @Column(name = "Preferred_Roles")
    private String preferredRoles;

    @Column(name = "Volunteer_Hours")
    private int volunteerHours;

    @Column(name = "Emergency_Contact_Name")
    private String emergencyContactName;

    @Column(name = "Emergency_Contact_Number")
    private String emergencyContactNumber;

    // Getters and Setters
    public int getVolunteerID() {
        return volunteerID;
    }

    public void setVolunteerID(int volunteerID) {
        this.volunteerID = volunteerID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPreferredRoles() {
        return preferredRoles;
    }

    public void setPreferredRoles(String preferredRoles) {
        this.preferredRoles = preferredRoles;
    }

    public Integer getVolunteerHours() {
        return volunteerHours;
    }

    public void setVolunteerHours(int volunteerHours) {
        this.volunteerHours = volunteerHours;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }
}
