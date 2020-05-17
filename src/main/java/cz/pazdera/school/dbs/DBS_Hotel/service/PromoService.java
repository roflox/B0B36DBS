package cz.pazdera.school.dbs.DBS_Hotel.service;

import cz.pazdera.school.dbs.DBS_Hotel.dao.PromoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromoService {

    private final PromoDao promoDao;

    @Autowired
    public PromoService(PromoDao promoDao) {
        this.promoDao = promoDao;
    }
}
