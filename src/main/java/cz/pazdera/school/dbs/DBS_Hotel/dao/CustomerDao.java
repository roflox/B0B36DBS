package cz.pazdera.school.dbs.DBS_Hotel.dao;

import cz.pazdera.school.dbs.DBS_Hotel.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class CustomerDao extends AbstractDao<Customer> {

    public CustomerDao() {
        super(Customer.class);
    }

    public Customer getCustomerByUsername(String username) {
        try {
            return em
                    .createQuery("SELECT c FROM Customer c " +
                            "JOIN CustomerDetails d " +
                            "ON c.id = d.customer.id " +
                            "WHERE d.username = :username", Customer.class)
                    .setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
