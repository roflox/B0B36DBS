package cz.pazdera.school.dbs.DBS_Hotel.service;

import cz.pazdera.school.dbs.DBS_Hotel.dao.CustomerDao;
import cz.pazdera.school.dbs.DBS_Hotel.dao.CustomerDetailsDao;
import cz.pazdera.school.dbs.DBS_Hotel.dto.LoginDto;
import cz.pazdera.school.dbs.DBS_Hotel.model.Customer;
import cz.pazdera.school.dbs.DBS_Hotel.model.CustomerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerService implements UserDetailsService {


    private final CustomerDao customerDao;
    private final CustomerDetailsDao detailsDao;
    private final BCryptPasswordEncoder bcrypt;

    @Autowired
    public CustomerService(CustomerDao customerDao, CustomerDetailsDao customerDetailsDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerDao = customerDao;
        this.detailsDao = customerDetailsDao;
        this.bcrypt = bCryptPasswordEncoder;
    }

    @Transactional
    public void persist(CustomerDetails details) {
        Objects.requireNonNull(details);
        if(this.detailsDao.findByUsername(details.getUsername())!=null){
            //todo
            return;
        }
        Customer customer = new Customer();
        customer.setCustomerDetails(details);
        details.setPassword(bcrypt.encode(details.getPassword()));
        details.setCustomer(customer);
        this.detailsDao.persist(details);
    }

    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    public CustomerDetails findById(Integer integer){
        return null;
    }

    public CustomerDetails findByUsername(String username){
        return this.detailsDao.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new LoginDto(this.detailsDao.findByUsername(username));
    }
}
