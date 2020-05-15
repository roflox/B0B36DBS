package cz.pazdera.school.dbs.DBS_Hotel.dao;

import cz.pazdera.school.dbs.DBS_Hotel.model.CustomerDetails;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDetailsDao extends AbstractDao<CustomerDetails>{

    public CustomerDetailsDao(){
        super(CustomerDetails.class);
    }
}
