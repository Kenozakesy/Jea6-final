package domain.server;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.sql.Types;

@Entity
public class Server {

    @Id
    @Column(unique=true, nullable=false, length=128)
    private long id;

    @Column(nullable=false, length=128)
    private String name;

    public Server(ServerDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
    }

    public Server() {
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
