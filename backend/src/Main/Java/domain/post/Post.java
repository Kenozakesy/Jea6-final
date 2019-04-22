package domain.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Post {

    @Id
    @Column(unique=true, nullable=false, length=128)
    private long id;

    @Column(nullable=false, length=128)
    private String message;

    public Post()
    {

    }

    public Post(PostDTO postDTO) {
        this.id = postDTO.getId();
        this.message = postDTO.getMessage();
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
}
