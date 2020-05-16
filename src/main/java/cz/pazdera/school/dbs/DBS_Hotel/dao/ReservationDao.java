package cz.pazdera.school.dbs.DBS_Hotel.dao;

import cz.pazdera.school.dbs.DBS_Hotel.model.Reservation;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDao extends AbstractDao{
    protected ReservationDao() {
        super(Reservation.class);
    }
}
