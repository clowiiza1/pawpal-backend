package co.za.pawpal.backend.service;

import co.za.pawpal.backend.dao.BookingDAO;
import co.za.pawpal.backend.entity.Booking;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingDAO bookingDAO;

    @Autowired
    public BookingServiceImpl(BookingDAO theBookingDAO) {
        this.bookingDAO = theBookingDAO;
    }

    @Override
    public List<Booking> findAll() {
        return bookingDAO.findAll();
    }

    @Override
    public Booking findById(int id) {
        return bookingDAO.findById(id);
    }

    @Transactional
    @Override
    public Booking save(Booking booking) {
        return bookingDAO.save(booking);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        bookingDAO.deleteById(id);
    }
}
