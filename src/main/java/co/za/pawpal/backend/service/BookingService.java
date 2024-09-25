package co.za.pawpal.backend.service;

import co.za.pawpal.backend.entity.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> findAll();
    Booking findById(int id);
    Booking save(Booking booking);
    void deleteById(int id);
}
