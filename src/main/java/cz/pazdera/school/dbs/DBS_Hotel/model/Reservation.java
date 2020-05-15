package cz.pazdera.school.dbs.DBS_Hotel.model;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation extends AbstractModel {

    @ManyToOne(optional = false)
    private Customer customer;

    @ManyToOne(
    )
    @JoinColumn(name = "promo_id")
    private PromoCode promoCode;

    @ManyToOne()
    private Room room;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PromoCode getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(PromoCode promoCode) {
        this.promoCode = promoCode;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}