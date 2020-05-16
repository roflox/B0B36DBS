package cz.pazdera.school.dbs.DBS_Hotel.controller.api.rest.v1;

import cz.pazdera.school.dbs.DBS_Hotel.dto.RegisterDto;
import cz.pazdera.school.dbs.DBS_Hotel.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static cz.pazdera.school.dbs.DBS_Hotel.config.GlobalVariables.REGISTER_URL;

@RestController
public class AuthController {

    private static final Logger console = LogManager.getLogger(AuthController.class);

    private final CustomerService customerService;

    @Autowired
    public AuthController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = REGISTER_URL,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCustomer(@RequestBody RegisterDto body) {
        this.customerService.persist(body.getDetails());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
