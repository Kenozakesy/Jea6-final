package domain.channel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Channel {

    @Id
    @Column(unique=true, nullable=false, length=128)
    private long id;

    @Column(nullable=false, length=128)
    private String name;

    public Channel() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
