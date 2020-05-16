package cz.pazdera.school.dbs.DBS_Hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractModel implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @CreationTimestamp
    @Column(name = "created")
    @JsonIgnore
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    @Column(name = "updated")
    @JsonIgnore
    private LocalDateTime updateDateTime;

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Objects.requireNonNull(id);
        this.id = id;
    }
}
