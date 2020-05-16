package cz.pazdera.school.dbs.DBS_Hotel.controller.api.rest.v1;

import cz.pazdera.school.dbs.DBS_Hotel.model.Customer;
import cz.pazdera.school.dbs.DBS_Hotel.model.CustomerDetails;
import cz.pazdera.school.dbs.DBS_Hotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cz.pazdera.school.dbs.DBS_Hotel.config.GlobalVariables.REGISTER_URL;

@RestController
@RequestMapping()
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},value = "/customers")
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST,value = REGISTER_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(){
        var temp = new CustomerDetails();
        temp.setUsername("wow.brog@gmail.com");
        temp.setPassword("test");
        this.customerService.persist(temp);
        System.out.println(temp);
    }

}
