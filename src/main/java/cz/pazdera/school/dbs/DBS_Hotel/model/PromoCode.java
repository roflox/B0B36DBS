package cz.pazdera.school.dbs.DBS_Hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "promo")
public class PromoCode extends AbstractModel{

    @Column(columnDefinition = "BOOL not null DEFAULT false")
    private boolean active;

    @Basic()
    private Integer discount;

    @Basic(optional = false)
    @Column(unique = true,nullable = false)
    private String name;

    @Basic()
    @Column(unique = true)
    private String code;

    @OneToMany(
            mappedBy = "promoCode"
    )
    @JsonIgnore
    private List<Reservation> reservations;




    public void addReservation(Reservation reservation){
        Objects.requireNonNull(reservation);
        if(this.reservations==null){
            this.reservations = new ArrayList<>();
        }
        this.reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
