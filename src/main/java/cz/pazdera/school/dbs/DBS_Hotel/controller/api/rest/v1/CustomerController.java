package cz.pazdera.school.dbs.DBS_Hotel.controller.api.rest.v1;

import cz.pazdera.school.dbs.DBS_Hotel.model.CustomerDetails;
import cz.pazdera.school.dbs.DBS_Hotel.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger console = LogManager.getLogger(CustomerController.class);
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Admin')")
    public CustomerDetails find(Authentication authentication){
        return customerService.findByUsername(authentication.getName());
    }

}
