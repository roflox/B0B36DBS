package cz.pazdera.school.dbs.DBS_Hotel.service;

import cz.pazdera.school.dbs.DBS_Hotel.dao.PromoDao;
import cz.pazdera.school.dbs.DBS_Hotel.dao.ReservationDao;
import cz.pazdera.school.dbs.DBS_Hotel.dao.RoomDao;
import cz.pazdera.school.dbs.DBS_Hotel.dao.UserDao;
import cz.pazdera.school.dbs.DBS_Hotel.dto.reservation.CreateReservationDto;
import cz.pazdera.school.dbs.DBS_Hotel.dto.reservation.UpdateReservationDto;
import cz.pazdera.school.dbs.DBS_Hotel.model.Reservation;
import cz.pazdera.school.dbs.DBS_Hotel.model.UserRole;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.naming.InsufficientResourcesException;
import javax.transaction.Transactional;
import java.time.LocalDate;

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
    public List<Reservation> getAll(Authentication auth){
        var user= userDao.getCustomerByUsername(auth.getName());
        if(user.getUserDetails().getRole()==UserRole.USER) {
            return reservationDao.getReservationsOfUser(user.getId());
        }else {
            return reservationDao.findAll();
        }
    }


    @Transactional
    public Reservation update(Integer id, Authentication auth, UpdateReservationDto dto) throws NotFoundException, InsufficientResourcesException {
        var reservation = get(id,auth);
        if(dto.startDate!=null){
            if(dto.duration==null){
                throw new InsufficientResourcesException("If you want to change startDate you have to specify duration");
            }
            reservation.setStartDate(dto.startDate);
        }
        if(dto.duration!=null){
            reservation.setEndDate(reservation.getStartDate().plusDays(dto.duration));
        }
        if(dto.numberOfPersons!=null){
            reservation.setNumberOfPersons(dto.numberOfPersons);
        }
        if(reservation.getRoom().getCapacity()<dto.numberOfPersons){
            throw new InsufficientResourcesException("Room does not have big enough capacity");
        }
        var reservations = reservationDao.getOverlapping(reservation.getRoom().getId(),reservation.getEndDate(),reservation.getStartDate());
        if(!reservations.isEmpty()){
            for(Reservation r: reservations){
                if(!reservation.getId().equals(r.getId())){
                    throw new InsufficientResourcesException("Room is already reserved in this time period");
                }
            }
        }
        reservationDao.update(reservation);
        return reservation;
    }

    @Transactional
    public Reservation create(CreateReservationDto dto, Authentication auth) throws NotFoundException, InsufficientResourcesException {
        var reservation = new Reservation();
        var room = roomDao.findByNumber(dto.roomNumber);
        if (room == null) {
            throw new NotFoundException("Room not found");
        }

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

        reservation.setStartDate(dto.startDate);
        reservation.setEndDate(dto.startDate.plusDays(dto.duration));
        var reservations = reservationDao.getOverlapping(room.getId(),reservation.getEndDate(),reservation.getStartDate());
        if(!reservations.isEmpty()) {
            throw new InsufficientResourcesException("Room is already reserved in this time period");
        }
        reservation.setAppUser(userDao.getCustomerByUsername(auth.getName()));
        reservation.setNumberOfPersons(dto.numberOfPersons);
        reservation.setRoom(room);
        reservation.setDeleted(false);
        reservationDao.persist(reservation);
        return reservation;
    }

    @Transactional
    public Reservation get(Integer id, Authentication auth) throws NotFoundException {
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

    @Transactional
    public void deleteReservation(Integer id, Authentication auth) throws NotFoundException {
        var tmp = get(id,auth);
        tmp.setDeleted(true);
        reservationDao.update(tmp);
    }

    @Transactional
    public void payReservation(Integer id, Authentication auth) throws NotFoundException{
        var tmp = get(id,auth);
        tmp.setPaid(true);
        reservationDao.update(tmp);
    }

    @Transactional
    public void sendFeedback(Integer id, Authentication auth, String feedback) throws NotFoundException, InsufficientResourcesException {
        var tmp = get(id,auth);
        if(tmp.getEndDate().isAfter(LocalDate.now())){
            throw new InsufficientResourcesException("You cannot send feedback before you check-out");
        }
        tmp.setFeedback(feedback);
        reservationDao.update(tmp);
    }

}
