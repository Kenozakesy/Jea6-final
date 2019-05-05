package domain.accountServer;

import domain.accoun.AccountDTO;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
public class AccountServer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false, length=128)
    private long accountId;

    @Column(nullable=false, length=128)
    private long serverId;

    public AccountServer() {

    }

    public AccountServer(long accountId, long serverId) {
        this.accountId = accountId;
        this.serverId = serverId;
    }

    public AccountServer(AccountServerDTO dto) {
        this.id = dto.getId();
        this.accountId = dto.getAccountId();
        this.serverId = dto.getServerId();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
    public long getServerId() {
        return serverId;
    }
    public void setServerId(long serverId) {
        serverId = serverId;
    }
}
