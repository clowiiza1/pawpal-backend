package co.za.pawpal.backend.dao;

import co.za.pawpal.backend.entity.Booking;

import java.util.List;

public interface BookingDAO {
    List<Booking> findAll();
    Booking findById(int id);
    Booking save(Booking booking);
    void deleteById(int id);

    List<Booking> findByUserId(int userId);
}
