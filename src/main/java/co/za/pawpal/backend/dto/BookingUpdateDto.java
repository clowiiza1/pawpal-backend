package co.za.pawpal.backend.dto;

import lombok.Data;

@Data
public class BookingUpdateDto {
    private int bookingID;
    private String status;
    // Include other fields if needed
}