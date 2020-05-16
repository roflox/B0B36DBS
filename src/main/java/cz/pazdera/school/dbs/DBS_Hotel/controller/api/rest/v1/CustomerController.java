package cz.pazdera.school.dbs.DBS_Hotel.controller.api.rest.v1;

import cz.pazdera.school.dbs.DBS_Hotel.model.Customer;
import cz.pazdera.school.dbs.DBS_Hotel.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping()
public class CustomerController {

    private static final Logger log = LogManager.getLogger(CustomerController.class);
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},value = "/customers")
    public List<Customer> findAll(){
        return customerService.findAll();
    }



}
