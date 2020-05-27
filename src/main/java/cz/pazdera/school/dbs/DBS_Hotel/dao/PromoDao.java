package cz.pazdera.school.dbs.DBS_Hotel.dao;

import cz.pazdera.school.dbs.DBS_Hotel.model.PromoCode;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class PromoDao extends AbstractDao<PromoCode> {
    protected PromoDao() {
        super(PromoCode.class);
    }

    public PromoCode findByName(String name) {
        try {
            return em
                    .createQuery("SELECT p FROM PromoCode p WHERE p.name = :name", PromoCode.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public PromoCode findByCode(String code) {
        try {
            return em
                    .createQuery("SELECT p FROM PromoCode p WHERE p.code = :code", PromoCode.class)
                    .setParameter("code", code).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<PromoCode> findAllActive() {
        try {
            return em
                    .createQuery("SELECT p FROM PromoCode p WHERE p.active = true", PromoCode.class)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void persistOrError(PromoCode promoCode) {
        var result = em
                .createQuery("SELECT p FROM PromoCode p WHERE p.name = :name or p.code = :code", PromoCode.class).setParameter("name", promoCode.getCode()).setParameter("code", promoCode.getCode()).getResultList();
        if (result.isEmpty()) {
            this.persist(promoCode);
        } else {
            throw new EntityExistsException("Promo with that code or name already exists");
        }
    }
}
