package cz.pazdera.school.dbs.DBS_Hotel.service;

import cz.pazdera.school.dbs.DBS_Hotel.dao.RoomDao;
import cz.pazdera.school.dbs.DBS_Hotel.dto.room.CreateRoomDto;
import cz.pazdera.school.dbs.DBS_Hotel.dto.room.UpdateRoomDto;
import cz.pazdera.school.dbs.DBS_Hotel.model.Room;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;


@Service
public class RoomService {

    private final RoomDao roomDao;

    @Autowired
    public RoomService(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Transactional
    public List<Room> getAll(){
        return roomDao.findAll();
    }

    @Transactional
    public Room persist(CreateRoomDto body){
        if(roomDao.findByNumber(body.number)!=null){
            throw new EntityExistsException("Room with that number already exists");
        }
        roomDao.persist(body.getRoom());
        return roomDao.findByNumber(body.number);
    }

    @Transactional
    public void delete(Integer number) throws NotFoundException {
        Objects.requireNonNull(number);
        var tmp = roomDao.findByNumber(number);
        if(tmp==null){
            throw new NotFoundException("Room with number "+number+" was not found");
        }
        this.roomDao.remove(tmp);
    }

    public Room get(Integer number) throws NotFoundException {
        var tmp = this.roomDao.findByNumber(number);
        if(tmp == null){
            throw new NotFoundException("Room with number "+number+" was not found");
        }
        return tmp;
    }

    @Transactional
    public Room update(Integer number, UpdateRoomDto body) throws NotFoundException{
        var tmp = this.roomDao.findByNumber(number);
        if(tmp==null){
            throw new NotFoundException("Room with number "+number+" was not found");
        }
        if (body.balcony != null){
            tmp.setBalcony(body.balcony);
        }
        if( body.television!=null){
            tmp.setTelevision(body.television);
        }
        if(body.capacity!=null){
            tmp.setCapacity(body.capacity);
        }
        if(body.price!=null){
            tmp.setPrice(body.price);
        }
        roomDao.update(tmp);
        return tmp;
    }

}
