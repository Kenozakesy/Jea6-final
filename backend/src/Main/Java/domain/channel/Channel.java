package domain.channel;

import domain.post.Post;

import javax.persistence.*;
import java.util.List;

@Entity
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false, length=128)
    private int serverId;

    @Column(nullable=false, length=128)
    private String name;

    public Channel() {
    }

    public Channel(ChannelDTO channelDTO) {
        this.id = channelDTO.getId();
        this.serverId = channelDTO.getServerId();
        this.name = channelDTO.getName();
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

    public int getServerId() {
        return serverId;
    }
    public void setServerId(int serverId) {
        this.serverId = serverId;
    }
}
