package cz.pazdera.school.dbs.DBS_Hotel.controller.api.rest.v1;


import cz.pazdera.school.dbs.DBS_Hotel.dto.CreateRoomDto;
import cz.pazdera.school.dbs.DBS_Hotel.model.Room;
import cz.pazdera.school.dbs.DBS_Hotel.service.RoomService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;
    private static final Logger console = LogManager.getLogger(RoomController.class);

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(@RequestBody CreateRoomDto body) {
        return roomService.persist(body);
    }

}
