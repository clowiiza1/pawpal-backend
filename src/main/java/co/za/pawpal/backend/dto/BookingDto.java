package co.za.pawpal.backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BookingDto {
    private Date bookingDate;
    private String bookingType;
    private int bookingTime;
}
