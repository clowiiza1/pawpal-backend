package co.za.pawpal.backend.rest;

import co.za.pawpal.backend.dto.BookingADto;
import co.za.pawpal.backend.dto.BookingDto;
import co.za.pawpal.backend.dto.BookingUpdateDto;
import co.za.pawpal.backend.entity.Booking;
import co.za.pawpal.backend.entity.VolunteerInfo;
import co.za.pawpal.backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingRestController {

    private final BookingService bookingService;

    @Autowired
    public BookingRestController(BookingService theBookingService) {
        this.bookingService = theBookingService;
    }

    @GetMapping("/bookings")
    public List<Booking> findAll() {
        return bookingService.findAll();
    }

    @GetMapping("/bookings/{bookingId}")
    public Booking getBooking(@PathVariable int bookingId) {
        Booking booking = bookingService.findById(bookingId);
        if (booking == null) {
            throw new RuntimeException("Booking ID not found - " + bookingId);
        }
        return booking;
    }

    @PostMapping("/booking")
    public Booking addBooking(@RequestBody BookingDto booking) {
        return bookingService.save(booking);
    }

    @PutMapping("/bookings")
    public Booking updateBooking(@RequestBody BookingDto booking) {
        return bookingService.save(booking);
    }

    @DeleteMapping("/bookings/{bookingId}")
    public String deleteBooking(@PathVariable int bookingId) {
        Booking booking = bookingService.findById(bookingId);
        if (booking == null) {
            throw new RuntimeException("Booking ID not found - " + bookingId);
        }

        bookingService.deleteById(bookingId);
        return "Deleted booking id - " + bookingId;
    }

    @PostMapping("/booking/volunteer")
    public Booking addVolunteerBooking(@RequestBody String date) {
        return bookingService.saveVolunteerBooking(date);
    }

    @PostMapping("/booking/adopter")
    public Booking addAdopterBooking(@RequestBody BookingADto bookingADto) {
        return bookingService.saveAdopterBooking(bookingADto);
    }

    @PutMapping("/bookings/{bookingId}/status")
    public Booking updateBookingStatus(@PathVariable int bookingId, @RequestBody BookingUpdateDto bookingUpdateDto) {
        return bookingService.updateBookingStatus(bookingId, bookingUpdateDto);
    }

    @GetMapping("/bookings/user")
    public List<Booking> getBookingsByUserId() {
        return bookingService.findByUserId();
    }


}
