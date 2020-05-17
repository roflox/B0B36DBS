package cz.pazdera.school.dbs.DBS_Hotel.model;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "room")
public class Room extends AbstractModel {

    @Basic(optional = false)
    @Column(unique = true, nullable = false)
    private Integer number;

    @Basic()
    private Integer capacity;

    @Basic()
    @Column(length = 8, precision = 2)
    private BigDecimal price;

    @OneToMany(
            mappedBy = "room"
    )
    private List<Reservation> reservations;

    public void addReservation(Reservation reservation) {
        Objects.requireNonNull(reservation);
        if (this.reservations == null) {
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


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
