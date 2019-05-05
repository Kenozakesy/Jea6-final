package domain.server;

import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.sql.Types;

@Entity
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false, length=128)
    private String name;

    public Server() {
    }

    public Server(ServerDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
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
