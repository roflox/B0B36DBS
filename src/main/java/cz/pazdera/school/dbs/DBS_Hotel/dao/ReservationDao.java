package cz.pazdera.school.dbs.DBS_Hotel.dao;

import cz.pazdera.school.dbs.DBS_Hotel.model.Reservation;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ReservationDao extends AbstractDao<Reservation>{
    protected ReservationDao() {
        super(Reservation.class);
    }


    public List<Reservation> getAllActiveReservationsForRoom(Integer id){
        try{
            return em.createQuery("SELECT r FROM Reservations r WHERE r.end_date > :current").setParameter("current",LocalDate.now()).getResultList();
        }catch (NoResultException e){
            return null;
        }
    }
}
