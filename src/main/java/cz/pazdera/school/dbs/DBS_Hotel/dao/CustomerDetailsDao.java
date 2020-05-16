package cz.pazdera.school.dbs.DBS_Hotel.dao;

import cz.pazdera.school.dbs.DBS_Hotel.model.CustomerDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Repository
public class CustomerDetailsDao extends AbstractDao<CustomerDetails> {

    public CustomerDetailsDao() {
        super(CustomerDetails.class);
    }

    public CustomerDetails findByEmail(String email) {
        try {
            return this.em
                    .createQuery("SELECT c FROM CustomerDetails c where c.username = :email", CustomerDetails.class)
                    .setParameter("email", email)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
