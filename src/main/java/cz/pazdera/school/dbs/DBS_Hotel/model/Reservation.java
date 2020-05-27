package cz.pazdera.school.dbs.DBS_Hotel.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class Reservation extends AbstractModel {

    @ManyToOne(optional = false)
    @JsonIgnore
    private AppUser appUser;

    @ManyToOne(
    )
    @JoinColumn(name = "promo_id")
    private PromoCode promoCode;

    @ManyToOne(optional = false)
    private Room room;

    @Basic(optional = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Basic(optional = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Basic(optional = false)
    @Column(nullable = false)
    private int numberOfPersons;

    @Basic()
    @Column()
    private String feedback;


    @JsonAlias("price")
    public BigDecimal getPrice(){
        var price = this.room.getPrice();
        if(this.promoCode!=null){
            var discount = ((double) (100-this.promoCode.getDiscount())/100);
            price = price.multiply(new BigDecimal(discount));
            price = price.setScale(2,RoundingMode.DOWN);
        }
        return price;
    }


    @Column(columnDefinition = "BOOL not null default false")
    private boolean paid;

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public boolean isOverlapping(Reservation reservation){
        return reservation.startDate.isBefore(this.endDate) && reservation.endDate.isAfter(this.startDate);
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(Integer numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "appUser=" + appUser +
                ", promoCode=" + promoCode +
                ", room=" + room +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", numberOfPersons=" + numberOfPersons +
                ", feedback='" + feedback + '\'' +
                ", paid=" + paid +
                '}';
    }
}