package domain.post;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false, length=128)
    private long channelId;

    @Column(nullable=false, length=128)
    private String message;

    public Post()
    {

    }

    public Post(PostDTO postDTO) {
        this.id = postDTO.getId();
        this.message = postDTO.getMessage();
        this.channelId = postDTO.getChannelId();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public long getChannelId() {
        return channelId;
    }
    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }
}
