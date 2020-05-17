package cz.pazdera.school.dbs.DBS_Hotel.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "app_user")
public class AppUser extends AbstractModel {


    @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.EAGER,mappedBy = "appUser")
    private UserDetails userDetails;

    @OneToMany(
            mappedBy = "appUser"
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

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
