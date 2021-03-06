package cz.pazdera.school.dbs.DBS_Hotel.controller.api.rest.v1;

import cz.pazdera.school.dbs.DBS_Hotel.dto.reservation.CreateFeedbackDto;
import cz.pazdera.school.dbs.DBS_Hotel.dto.reservation.CreateReservationDto;
import cz.pazdera.school.dbs.DBS_Hotel.dto.reservation.UpdateReservationDto;
import cz.pazdera.school.dbs.DBS_Hotel.model.Reservation;
import cz.pazdera.school.dbs.DBS_Hotel.service.ReservationService;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.naming.InsufficientResourcesException;
import javax.validation.Valid;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private static final Logger console = LogManager.getLogger(ReservationController.class);

    private final ReservationService service;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.service = reservationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN','USER','EMPLOYEE')")
    public Reservation createReservation(@Valid @RequestBody CreateReservationDto body, Authentication authorization) throws NotFoundException, InsufficientResourcesException {
        return this.service.create(body, authorization);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
    public Reservation get(Authentication authentication, @PathVariable(value = "id") Integer id) throws NotFoundException {
        return this.service.get(id, authentication);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> getAll(Authentication authentication){
        return this.service.getAll(authentication);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(Authentication authentication, @PathVariable(value = "id") Integer id) throws NotFoundException {
        this.service.deleteReservation(id, authentication);
    }

    @PutMapping(value = "/{id}/pay")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void payReservation(Authentication authentication, @PathVariable(value = "id") Integer id) throws NotFoundException {
        this.service.payReservation(id, authentication);
    }

    @PostMapping(value = "/{id}/feedback")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendFeedback(Authentication authentication, @PathVariable(value = "id") Integer id, @Valid @RequestBody CreateFeedbackDto body) throws NotFoundException, InsufficientResourcesException {
        this.service.sendFeedback(id, authentication, body.feedback);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(Authentication authentication, @PathVariable(value = "id") Integer id, @Valid @RequestBody UpdateReservationDto body) throws NotFoundException, InsufficientResourcesException {
        this.service.update(id, authentication,body);
    }

}
