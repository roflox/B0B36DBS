package cz.pazdera.school.dbs.DBS_Hotel.dao;

import cz.pazdera.school.dbs.DBS_Hotel.model.PromoCode;

public class PromoDao extends AbstractDao{
    protected PromoDao(Class type) {
        super(PromoCode.class);
    }
}
