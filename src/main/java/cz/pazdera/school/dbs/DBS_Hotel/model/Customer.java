package cz.pazdera.school.dbs.DBS_Hotel.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer extends AbstractModel {


    @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER,mappedBy = "customer")
    private CustomerDetails customerDetails;

    @OneToMany(
            mappedBy = "customer"
    )
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

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }
}
