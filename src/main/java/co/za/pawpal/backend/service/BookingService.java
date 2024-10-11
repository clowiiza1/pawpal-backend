package co.za.pawpal.backend.service;

import co.za.pawpal.backend.dto.BookingADto;
import co.za.pawpal.backend.dto.BookingDto;
import co.za.pawpal.backend.dto.BookingUpdateDto;
import co.za.pawpal.backend.entity.Booking;
import jakarta.transaction.Transactional;

import java.util.List;

public interface BookingService {
    List<Booking> findAll();
    Booking findById(int id);
    Booking save(BookingDto bookingDto);
    Booking saveVolunteerBooking(String date);
    void deleteById(int id);

    @Transactional
    Booking updateBookingStatus(int bookingId, BookingUpdateDto bookingUpdateDto);





    List<Booking> findByUserId();

    @Transactional
    Booking saveAdopterBooking(BookingADto bookingDto);

}
