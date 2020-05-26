package cz.pazdera.school.dbs.DBS_Hotel.dao;

import cz.pazdera.school.dbs.DBS_Hotel.model.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.time.LocalDate;

@Repository
public class RoomDao extends AbstractDao<Room> {
    protected RoomDao() {
        super(Room.class);
    }

    public Room findByNumber(int number) {
        try {
            return em
                    .createQuery("SELECT r FROM Room r WHERE r.number = :number", Room.class)
                    .setParameter("number", number).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Room findEmptyRoom(LocalDate startDate, int duration, int capacity){
        try{
            return em.createQuery("SELECT r from Room r JOIN Reservation res where r.capacity>= :capacity and ",Room.class).setParameter("capacity",capacity).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
}
