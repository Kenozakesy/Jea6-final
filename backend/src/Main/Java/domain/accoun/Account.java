package domain.accoun;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@NamedQueries({
        @NamedQuery(name = "login", query = "select u from Account u where u.name = :name  AND u.password = :password")
}
)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true, nullable=false, length=128)
    private String name;

    @Column(nullable=false, length=128)
    private String password;

    public Account() {
    }

    public Account(AccountDTO accountDTO) {

        //message hash
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(accountDTO.getPassword().getBytes());
        String encryptedString = new String(messageDigest.digest());

        this.id = accountDTO.getId();
        this.name = accountDTO.getName();
        this.password = encryptedString;
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

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
