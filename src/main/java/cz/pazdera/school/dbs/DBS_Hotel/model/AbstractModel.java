package cz.pazdera.school.dbs.DBS_Hotel.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractModel implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        Objects.requireNonNull(id);
        this.id = id;
    }
}
