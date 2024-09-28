package co.za.pawpal.backend.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingID")
    private int bookingID;

    @Column(name = "Booking_Slot")
    @Temporal(TemporalType.DATE)
    private Date bookingSlot;

    @Column(name = "Status")
    private String status;

    @Column(name = "UserID")
    private int userID;

    @Column(name = "AnimalID")
    private int animalID;

    @Column(name = "Booking_Type")
    private String bookingType;

    @Column(name = "Time_Slot")
    private int timeSlot;

    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getTimeSlot() {
        return timeSlot;
    }

    // Getters and Setters
    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public Date getBookingSlot() {
        return bookingSlot;
    }

    public void setBookingSlot(Date bookingSlot) {
        this.bookingSlot = bookingSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAnimalID() {
        return animalID;
    }

    public void setAnimalID(Integer animalID) {
        this.animalID = animalID;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }
}
