package cz.pazdera.school.dbs.DBS_Hotel.dao;

import cz.pazdera.school.dbs.DBS_Hotel.model.UserDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class UserDetailsDao extends AbstractDao<UserDetails> {

    public UserDetailsDao() {
        super(UserDetails.class);
    }

    public UserDetails findByUsername(String username) {
        try {
            return this.em
                    .createQuery("SELECT c FROM UserDetails c where c.username = :username", UserDetails.class)
                    .setParameter("username", username)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public UserDetails findByCustomerId(Integer id){
        try {
            return this.em.createQuery("SELECT details FROM UserDetails details WHERE details.customer.id = :id", UserDetails.class)
                    .setParameter("id",id)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
