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


    public List<Reservation> getOverlapping(Integer id, LocalDate endDate, LocalDate startDate){
        try{
            return em.createQuery("SELECT r FROM Reservation r WHERE r.startDate < :end and r.endDate> :start and r.room.id = :id and r.deleted = false",Reservation.class).setParameter("end", endDate).setParameter("start",startDate).setParameter("id",id).getResultList();
        }catch (NoResultException e){
            return null;
        }
    }

    public List<Reservation> getReservationsOfUser(Integer id){
        try{
            return em.createQuery("SELECT r FROM Reservation r WHERE r.user.id = :id",Reservation.class).setParameter("id",id).getResultList();
        }catch (NoResultException e){
            return null;
        }
    }
}
