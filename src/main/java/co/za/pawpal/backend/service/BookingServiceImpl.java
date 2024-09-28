package co.za.pawpal.backend.service;

import co.za.pawpal.backend.dao.BookingDAO;
import co.za.pawpal.backend.dao.UserDAO;
import co.za.pawpal.backend.dto.BookingDto;
import co.za.pawpal.backend.entity.Booking;
import co.za.pawpal.backend.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

import static java.sql.Types.NULL;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingDAO bookingDAO;
    private final UserDAO userDAO;


    public Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return userDAO.findByUsername(authentication.getName());
        }

        return null; // or throw an exception, depending on your needs
    }

    @Autowired
    public BookingServiceImpl(BookingDAO theBookingDAO, UserDAO userDAO) {
        this.bookingDAO = theBookingDAO;
        this.userDAO = userDAO;
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
    public Booking save(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setBookingType(bookingDto.getBookingType());
        booking.setBookingSlot(bookingDto.getBookingDate());
        booking.setTimeSlot(99);
        booking.setUserID(getCurrentUser().get().getId());
        booking.setAnimalID(NULL);
        booking.setStatus("Pending");
        return bookingDAO.save(booking);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        bookingDAO.deleteById(id);
    }
}
