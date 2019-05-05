package repository;

import domain.accoun.Account;
import domain.accountServer.AccountServer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AccountServerRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(AccountServer accountServer) {
        em.persist(accountServer);
    }

    public AccountServer find(long id) {
        return em.find(AccountServer.class, id);
    }

    public List<AccountServer> findAll() {
        return em.createQuery("SELECT l FROM AccountServer l", AccountServer.class)
                .getResultList();
    }

    public void update(AccountServer accountServer) {
        em.merge(accountServer);
    }

    public void detach(AccountServer accountServer) {
        em.detach(accountServer);
    }

    public void delete(long id) {
        AccountServer accountToRemove = em.find(AccountServer.class, id);
        em.remove(accountToRemove);
    }
}
