package cz.pazdera.school.dbs.DBS_Hotel.controller.api.rest.v1;


import cz.pazdera.school.dbs.DBS_Hotel.dto.room.CreateRoomDto;
import cz.pazdera.school.dbs.DBS_Hotel.dto.room.UpdateRoomDto;
import cz.pazdera.school.dbs.DBS_Hotel.model.Room;
import cz.pazdera.school.dbs.DBS_Hotel.service.RoomService;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces  = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Room createRoom(@Valid @RequestBody CreateRoomDto body) {
        return roomService.persist(body);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{number}")
    public void deleteRoom(@PathVariable(value = "number") Integer number) throws NotFoundException {
        this.roomService.delete(number);
    }


    @GetMapping(value = "/{number}",produces  = MediaType.APPLICATION_JSON_VALUE)
    public Room getRoom(@PathVariable(value = "number") Integer number) throws NotFoundException {
        return this.roomService.get(number);
    }

    @GetMapping(produces  = MediaType.APPLICATION_JSON_VALUE)
    public List<Room> getAllRooms(){
        return this.roomService.getAll();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,value = "/{number}",produces  = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Room updateRoom(@PathVariable(value = "number") Integer number,@Valid @RequestBody UpdateRoomDto body) throws NotFoundException {
        return this.roomService.update(number,body);
    }

}
