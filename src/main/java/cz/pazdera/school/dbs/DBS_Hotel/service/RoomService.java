package cz.pazdera.school.dbs.DBS_Hotel.service;

import cz.pazdera.school.dbs.DBS_Hotel.dao.RoomDao;
import cz.pazdera.school.dbs.DBS_Hotel.dto.CreateRoomDto;
import cz.pazdera.school.dbs.DBS_Hotel.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class RoomService {

    private final RoomDao roomDao;

    @Autowired
    public RoomService(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Transactional
    public Room persist(CreateRoomDto body){
        this.roomDao.persist(body.getRoom());
        return roomDao.findByNumber(body.number);
    }
}
