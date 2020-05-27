package cz.pazdera.school.dbs.DBS_Hotel.service;

import cz.pazdera.school.dbs.DBS_Hotel.dao.PromoDao;
import cz.pazdera.school.dbs.DBS_Hotel.dao.ReservationDao;
import cz.pazdera.school.dbs.DBS_Hotel.dao.RoomDao;
import cz.pazdera.school.dbs.DBS_Hotel.dao.UserDao;
import cz.pazdera.school.dbs.DBS_Hotel.dto.reservation.CreateReservationDto;
import cz.pazdera.school.dbs.DBS_Hotel.model.Reservation;
import cz.pazdera.school.dbs.DBS_Hotel.model.UserRole;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import javax.transaction.Transactional;

@Service
public class ReservationService {


    private final UserDao userDao;
    private final ReservationDao reservationDao;
    private final PromoDao promoDao;
    private final RoomDao roomDao;
    private static final Logger console = LogManager.getLogger(ReservationService.class);

    @Autowired
    public ReservationService(UserDao userDao, ReservationDao reservationDao, PromoDao promoDao, RoomDao roomDao) {
        this.userDao = userDao;
        this.reservationDao = reservationDao;
        this.promoDao = promoDao;
        this.roomDao = roomDao;
    }


    @Transactional
    public Reservation createReservation(CreateReservationDto dto, Authentication auth) throws NotFoundException, InsufficientResourcesException {
        var reservation = new Reservation();
        var room = roomDao.findByNumber(dto.roomNumber);
        if (room == null) {
            throw new NotFoundException("Room not found");
        }
        var reservations = reservationDao.getAllActiveReservationsForRoom(room.getId());
        //todo check if room is empty
        if (dto.promoCode != null) {
            if(dto.promoCode.length()!=0) {
                var promo = promoDao.findByCode(dto.promoCode);
                if (promo == null) {
                    throw new NotFoundException("Promo not found");
                }
                reservation.setPromoCode(promo);
            }
        }
        if(room.getCapacity()<dto.numberOfPersons){
            throw new InsufficientResourcesException("Room does not have big enough capacity");
        }
        reservation.setNumberOfPersons(dto.numberOfPersons);
        reservation.setEndDate(dto.startDate.plusDays(dto.duration));
        for(Reservation res: reservations){
            if(res.isOverlapping(reservation)){
                throw new InsufficientResourcesException("Room is already reserved in this time period");
            }
        }
        reservation.setAppUser(userDao.getCustomerByUsername(auth.getName()));
        reservation.setStartDate(dto.startDate);
        reservation.setRoom(room);
        reservationDao.persist(reservation);
        return reservation;
    }

    @Transactional
    public Reservation getReservation(Integer id, Authentication auth) throws NotFoundException {
        var tmp = reservationDao.find(id);
        var user = userDao.getCustomerByUsername(auth.getName());
        if (tmp == null) {
            throw new NotFoundException("Reservation not found");
        }
        if (user.getUserDetails().getRole() == UserRole.USER) {
            if (!tmp.getAppUser().getId().equals(user.getId())) {
                throw new NotFoundException("Reservation not found");
            }
        }
        return tmp;
    }

}
