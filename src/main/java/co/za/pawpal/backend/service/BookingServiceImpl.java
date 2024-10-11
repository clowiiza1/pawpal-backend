package co.za.pawpal.backend.service;
import co.za.pawpal.backend.dto.BookingADto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import co.za.pawpal.backend.dao.BookingDAO;
import co.za.pawpal.backend.dao.UserDAO;
import co.za.pawpal.backend.dto.BookingDto;
import co.za.pawpal.backend.dto.BookingUpdateDto;
import co.za.pawpal.backend.entity.Booking;
import co.za.pawpal.backend.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public Booking saveVolunteerBooking(String dateString) {
        Booking booking = new Booking();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Optional<User> currentUser = getCurrentUser();
        if (!currentUser.isPresent()) {
            throw new RuntimeException("No current user found for the booking");
        }

        User user = currentUser.get();
        System.out.println("Current user ID: " + user.getId());

        try {
            // Directly parse the date string into a Date object
            Date date = formatter.parse(dateString);

            // Set the booking details
            booking.setBookingType("Volunteer");
            booking.setBookingSlot(date);
            booking.setTimeSlot(1);  // Example time slot
            booking.setUserID(user.getId());
            booking.setAnimalID(0);  // Set appropriate value here, if needed
            booking.setStatus("Pending");
        } catch (ParseException e) {
            // Handle parsing error if the date format is incorrect
            e.printStackTrace();
            throw new RuntimeException("Invalid date format, expected yyyy-MM-dd", e);
        }

        // Save the booking using the DAO
        return bookingDAO.save(booking);
    }




    @Transactional
    @Override
    public void deleteById(int id) {
        bookingDAO.deleteById(id);
    }

    @Transactional
    @Override
    public Booking updateBookingStatus(int bookingId, BookingUpdateDto bookingUpdateDto) {
        Booking existingBooking = bookingDAO.findById(bookingId);
        if (existingBooking == null) {
            throw new RuntimeException("Booking ID not found - " + bookingId);
        }
        existingBooking.setStatus(bookingUpdateDto.getStatus());
        // Update other fields if necessary
        return bookingDAO.save(existingBooking);
    }

    @Override
    public List<Booking> findByUserId() {
        int userId = getCurrentUser().get().getId();
        return bookingDAO.findByUserId(userId);
    }

    @Transactional
    @Override
    public Booking saveAdopterBooking(BookingADto bookingADto) {
        Booking booking = new Booking();

        // Check for the current user
        Optional<User> currentUser = getCurrentUser();
        if (!currentUser.isPresent()) {
            throw new RuntimeException("No current user found for the booking");
        }
        User user = currentUser.get();

        // Set the booking information
        booking.setBookingType("Adopter");
        booking.setBookingSlot(bookingADto.getBookingDate());
        booking.setTimeSlot(1); // Assuming some default value for time slot
        booking.setUserID(user.getId());
        booking.setAnimalID(bookingADto.getAnimalId());
        booking.setStatus("Pending");
        booking.setBookingType("Adopter");

        // Save the booking
        return bookingDAO.save(booking);
    }

}
