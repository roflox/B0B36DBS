package cz.pazdera.school.dbs.DBS_Hotel.dao;

import cz.pazdera.school.dbs.DBS_Hotel.model.AppUser;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class UserDao extends AbstractDao<AppUser> {

    public UserDao() {
        super(AppUser.class);
    }

    public AppUser getCustomerByUsername(String username) {
        try {
            return em
                    .createQuery("SELECT c FROM AppUser c " +
                            "JOIN UserDetails d " +
                            "ON c.id = d.appUser.id " +
                            "WHERE d.username = :username", AppUser.class)
                    .setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
