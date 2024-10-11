package co.za.pawpal.backend.dao;

import co.za.pawpal.backend.entity.Booking;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookingDAOImpl implements BookingDAO {

    private EntityManager entityManager;

    @Autowired
    public BookingDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Booking> findAll() {
        TypedQuery<Booking> query = entityManager.createQuery("SELECT b FROM Booking b", Booking.class);
        return query.getResultList();
    }

    @Override
    public Booking findById(int id) {
        return entityManager.find(Booking.class, id);
    }

    @Override
    public Booking save(Booking booking) {
        if (booking.getBookingID() == null) {  // Use == for comparison with null
            // For new entities
            System.out.println("Persisting new booking: " + booking);
            entityManager.persist(booking);
        } else {
            // For existing entities
            System.out.println("Merging existing booking: " + booking.getBookingID());
            entityManager.merge(booking);
        }
        return booking;
    }

    @Override
    public void deleteById(int id) {
        Booking booking = entityManager.find(Booking.class, id);
        if (booking != null) {
            entityManager.remove(booking);
        }
    }

    @Override
    public List<Booking> findByUserId(int userId) {
        TypedQuery<Booking> query = entityManager.createQuery(
                "SELECT b FROM Booking b WHERE b.userID = :userId", Booking.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}
