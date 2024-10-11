package co.za.pawpal.backend.dto;

import java.util.Date;

public class BookingADto {
    private Date bookingDate;
    private Integer animalId;



    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Integer getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }
}
