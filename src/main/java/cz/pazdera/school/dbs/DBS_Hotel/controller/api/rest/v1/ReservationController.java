package cz.pazdera.school.dbs.DBS_Hotel.controller.api.rest.v1;

import cz.pazdera.school.dbs.DBS_Hotel.dto.CreateReservationDto;
import cz.pazdera.school.dbs.DBS_Hotel.service.ReservationService;
import javassist.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


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
    public void createReservation(@RequestBody CreateReservationDto body, Authentication authorization) throws NotFoundException {
        this.service.createReservation(body,authorization);
    }

}
