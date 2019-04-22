package repository;

import domain.accoun.Account;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AccountRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Account account) {
        em.persist(account);
    }

    public Account find(long id) {
        return em.find(Account.class, id);
    }

    public List<Account> findAll() {
        return em.createQuery("SELECT l FROM Account l", Account.class)
                .getResultList();
    }

    public void update(Account account) {
        em.merge(account);
    }

    public void detach(Account account) {
        em.detach(account);
    }

    public void delete(long id) {
        Account accountToRemove = em.find(Account.class, id);
        em.remove(accountToRemove);
    }

    //Non standard methods
    public Account getUser(String name, String password) {

        return em.createNamedQuery("login", Account.class)
                .setParameter("name", name)
                .setParameter("password", password)
                .getSingleResult();
    }
}
