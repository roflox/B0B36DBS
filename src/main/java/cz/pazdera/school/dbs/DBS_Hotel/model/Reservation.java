package cz.pazdera.school.dbs.DBS_Hotel.model;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Basic(optional = false)
    private LocalDateTime startDate;

    @Basic(optional = false)
    @Column(nullable = false)
    private Integer duration;

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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}