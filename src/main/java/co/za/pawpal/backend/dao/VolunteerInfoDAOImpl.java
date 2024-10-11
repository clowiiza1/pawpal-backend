package co.za.pawpal.backend.dao;

import co.za.pawpal.backend.entity.VolunteerInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VolunteerInfoDAOImpl implements VolunteerInfoDAO {

    private EntityManager entityManager;

    @Autowired
    public VolunteerInfoDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<VolunteerInfo> findAll() {
        TypedQuery<VolunteerInfo> theQuery = entityManager.createQuery("SELECT v FROM VolunteerInfo v", VolunteerInfo.class);
        return theQuery.getResultList();
    }

    @Override
    public VolunteerInfo findById(int id) {
        return entityManager.find(VolunteerInfo.class, id);
    }

    @Override
    public VolunteerInfo findByUserId(int userId) {
        String queryStr = "SELECT v FROM VolunteerInfo v WHERE v.userID = :userId";
        TypedQuery<VolunteerInfo> query = entityManager.createQuery(queryStr, VolunteerInfo.class);
        query.setParameter("userId", userId);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;  // or handle the exception as needed
        }
    }

    @Override
    public VolunteerInfo save(VolunteerInfo volunteerInfo) {
        return entityManager.merge(volunteerInfo);
    }

    @Override
    public void deleteById(int id) {
        VolunteerInfo volunteerInfo = entityManager.find(VolunteerInfo.class, id);
        if (volunteerInfo != null) {
            entityManager.remove(volunteerInfo);
        }
    }


}
