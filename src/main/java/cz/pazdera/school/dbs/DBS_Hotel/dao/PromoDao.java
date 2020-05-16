package cz.pazdera.school.dbs.DBS_Hotel.dao;

import cz.pazdera.school.dbs.DBS_Hotel.model.PromoCode;
import org.springframework.stereotype.Repository;

@Repository
public class PromoDao extends AbstractDao{
    protected PromoDao() {
        super(PromoCode.class);
    }

    public PromoCode find(Integer id) {
        return (PromoCode) super.find(id);
    }
}
