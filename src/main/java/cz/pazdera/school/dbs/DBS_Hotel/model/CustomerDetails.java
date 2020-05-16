package cz.pazdera.school.dbs.DBS_Hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.pazdera.school.dbs.DBS_Hotel.dto.RegisterDto;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "customer_details")
public class CustomerDetails extends AbstractModel {

    @Basic(optional = false)
    @Column(nullable = false,unique = true)
    @Email
    private String username;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Customer customer;


    @Basic(optional = false)
    @JsonIgnore
    private String password;

    public CustomerDetails() {}

    public CustomerDetails(RegisterDto dto){
        this.password = dto.password;
        this.username = dto.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

}
