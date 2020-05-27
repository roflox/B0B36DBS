package cz.pazdera.school.dbs.DBS_Hotel.service;

import cz.pazdera.school.dbs.DBS_Hotel.dao.PromoDao;
import cz.pazdera.school.dbs.DBS_Hotel.dto.promo.CreatePromoDto;
import cz.pazdera.school.dbs.DBS_Hotel.dto.promo.UpdatePromoDto;
import cz.pazdera.school.dbs.DBS_Hotel.model.PromoCode;
import javassist.NotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class PromoService {

    private final PromoDao promoDao;

    @Autowired
    public PromoService(PromoDao promoDao) {
        this.promoDao = promoDao;
    }

    @Transactional
    public List<PromoCode> getAll(){
        return promoDao.findAll();
    }

    @Transactional
    public List<PromoCode> getAllActive(){
        return promoDao.findAllActive();
    }

    @Transactional
    public PromoCode persist(CreatePromoDto body){
        promoDao.persistOrError(body.getPromo());
        return promoDao.findByName(body.name);
    }

    @Transactional
    public void delete(Integer  id) throws NotFoundException {
        var tmp = promoDao.find(id);
        if(tmp==null){
            throw new NotFoundException("Promo with id "+id+" was not found");
        }
        this.promoDao.remove(tmp);
    }

    @Transactional()
    public PromoCode get(Integer id) throws NotFoundException {
        var tmp = this.promoDao.find(id);
        if(tmp == null){
            throw new NotFoundException("Promo with id "+id+" was not found");
        }
        return tmp;
    }

    @Transactional
    public PromoCode update(Integer id, UpdatePromoDto body) throws NotFoundException{
        var tmp = this.promoDao.find(id);
        if(tmp==null){
            throw new NotFoundException("Promo with id "+id+" was not found");
        }
        if(body.name!=null){
            tmp.setName(body.name);
        }
        if(body.active!=null){
            tmp.setActive(body.active);
        }
        if(body.code!=null){
            tmp.setCode(body.code);
        }
        if(body.discount!=null){
            tmp.setDiscount(body.discount);
        }
        promoDao.update(tmp);
        return tmp;
    }
}
