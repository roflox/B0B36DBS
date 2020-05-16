package cz.pazdera.school.dbs.DBS_Hotel.service;

import cz.pazdera.school.dbs.DBS_Hotel.dao.CustomerDao;
import cz.pazdera.school.dbs.DBS_Hotel.dao.PromoDao;
import cz.pazdera.school.dbs.DBS_Hotel.dao.ReservationDao;
import cz.pazdera.school.dbs.DBS_Hotel.dao.RoomDao;
import cz.pazdera.school.dbs.DBS_Hotel.dto.CreateReservationDto;
import cz.pazdera.school.dbs.DBS_Hotel.model.PromoCode;
import cz.pazdera.school.dbs.DBS_Hotel.model.Reservation;
import cz.pazdera.school.dbs.DBS_Hotel.model.Room;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

import static cz.pazdera.school.dbs.DBS_Hotel.config.GlobalVariables.NOT_FOUND;
import static cz.pazdera.school.dbs.DBS_Hotel.config.GlobalVariables.NOT_SPECIFIED;
import static java.lang.String.format;
import static java.lang.String.valueOf;

@Service
public class ReservationService {


    private final CustomerDao customerDao;
    private final ReservationDao reservationDao;
    private final PromoDao promoDao;
    private final RoomDao roomDao;
    private static final Logger console = LogManager.getLogger(ReservationService.class);

    @Autowired
    public ReservationService(CustomerDao customerDao, ReservationDao reservationDao, PromoDao promoDao, RoomDao roomDao) {
        this.customerDao = customerDao;
        this.reservationDao = reservationDao;
        this.promoDao = promoDao;
        this.roomDao = roomDao;
    }


    @Transactional
    public void createReservation(CreateReservationDto dto, Authentication auth) throws NotFoundException {
        Objects.requireNonNull(dto);
        Objects.requireNonNull(dto.duration, format(NOT_SPECIFIED, Reservation.class.getSimpleName(), "duration"));
        Objects.requireNonNull(dto.numberOfPersons, format(NOT_SPECIFIED, Reservation.class.getSimpleName(), "numberOfPersons"));
        Objects.requireNonNull(dto.roomNumber, format(NOT_SPECIFIED, Reservation.class.getSimpleName(), "roomNumber"));
        Objects.requireNonNull(dto.startDate, format(NOT_SPECIFIED, Reservation.class.getSimpleName(), "startDate"));
        var reservation = new Reservation();
        var room = roomDao.findByNumber(dto.roomNumber);
        if(room==null){
            throw new NotFoundException("Room not found");
        }
        if(dto.promoId!=null){
            var promo =  promoDao.find(dto.promoId);
            if(promo==null){
                throw new NotFoundException("Promo not found");
            }
            reservation.setPromoCode(promo);
        }
        reservation.setNumberOfPersons(dto.numberOfPersons);
        reservation.setDuration(dto.duration);
        reservation.setCustomer(customerDao.getCustomerByUsername(auth.getName()));
        reservation.setStartDate(dto.startDate);
        reservationDao.persist(reservation);
    }


}
