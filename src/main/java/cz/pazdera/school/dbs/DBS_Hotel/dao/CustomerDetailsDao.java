package cz.pazdera.school.dbs.DBS_Hotel.dao;

import cz.pazdera.school.dbs.DBS_Hotel.model.CustomerDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class CustomerDetailsDao extends AbstractDao<CustomerDetails> {

    public CustomerDetailsDao() {
        super(CustomerDetails.class);
    }

    public CustomerDetails findByUsername(String username) {
        try {
            return this.em
                    .createQuery("SELECT c FROM CustomerDetails c where c.username = :username", CustomerDetails.class)
                    .setParameter("username", username)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public CustomerDetails findByCustomerId(Integer id){
        try {
            return this.em.createQuery("SELECT details FROM CustomerDetails details WHERE details.customer.id = :id",CustomerDetails.class)
                    .setParameter("id",id)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
