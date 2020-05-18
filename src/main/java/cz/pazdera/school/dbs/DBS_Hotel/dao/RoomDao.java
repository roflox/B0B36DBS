package cz.pazdera.school.dbs.DBS_Hotel.dao;

import cz.pazdera.school.dbs.DBS_Hotel.model.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

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
}
