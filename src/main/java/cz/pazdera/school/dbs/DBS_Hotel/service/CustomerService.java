package cz.pazdera.school.dbs.DBS_Hotel.service;

import cz.pazdera.school.dbs.DBS_Hotel.dao.CustomerDao;
import cz.pazdera.school.dbs.DBS_Hotel.dao.CustomerDetailsDao;
import cz.pazdera.school.dbs.DBS_Hotel.model.Customer;
import cz.pazdera.school.dbs.DBS_Hotel.model.CustomerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {


    private final CustomerDao customerDao;
    private final CustomerDetailsDao detailsDao;

    @Autowired
    public CustomerService(CustomerDao customerDao, CustomerDetailsDao customerDetailsDao) {
        this.customerDao = customerDao;
        this.detailsDao = customerDetailsDao;
    }

    @Transactional
    public void persist(CustomerDetails details){
        Objects.requireNonNull(details);
        Customer customer = new Customer();
        customer.setCustomerDetails(details);
        details.setCustomer(customer);
        this.detailsDao.persist(details);
    }

    public List<Customer> findAll(){
        return customerDao.findAll();
    }
}
