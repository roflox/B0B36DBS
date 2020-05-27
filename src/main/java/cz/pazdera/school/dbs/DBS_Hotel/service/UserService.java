package cz.pazdera.school.dbs.DBS_Hotel.service;

import cz.pazdera.school.dbs.DBS_Hotel.dao.UserDao;
import cz.pazdera.school.dbs.DBS_Hotel.dao.UserDetailsDao;
import cz.pazdera.school.dbs.DBS_Hotel.model.AppUser;
import cz.pazdera.school.dbs.DBS_Hotel.model.UserDetails;
import cz.pazdera.school.dbs.DBS_Hotel.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {


    private final UserDao userDao;
    private final UserDetailsDao detailsDao;
    private final BCryptPasswordEncoder bcrypt;

    @Autowired
    public UserService(UserDao userDao, UserDetailsDao userDetailsDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.detailsDao = userDetailsDao;
        this.bcrypt = bCryptPasswordEncoder;
    }

    @Transactional
    public void persist(UserDetails details) throws EntityExistsException {
        if(this.detailsDao.findByUsername(details.getUsername())!=null){
            throw new EntityExistsException("User with that username already exists");
        }
        AppUser appUser = new AppUser();
        appUser.setUserDetails(details);
        details.setPassword(bcrypt.encode(details.getPassword()));
        details.setAppUser(appUser);
        this.detailsDao.persist(details);
    }

    @Transactional
    public void createAdmin(){
        if(this.detailsDao.findByUsername("admin")!=null){
            return;
        }
        var adminUser = new AppUser();
        var admin = new UserDetails();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole(UserRole.ADMIN);
        admin.setAppUser(adminUser);
        this.detailsDao.persist(admin);
    }

    public List<AppUser> findAll() {
        return userDao.findAll();
    }

    public User findById(Integer integer){
        return null;
    }

    public UserDetails findByUsername(String username){
        return this.detailsDao.findByUsername(username);
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserDetails details = this.detailsDao.findByUsername(username);
        if(details==null){
            throw new UsernameNotFoundException("User with username "+username+" was not found.");
        }
        var temp = new ArrayList<SimpleGrantedAuthority>();
        temp.add(new SimpleGrantedAuthority(details.getRole().toString()));
        return new User(details.getUsername(),details.getPassword(),temp);
    }


}
