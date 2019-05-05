package repository;

import domain.server.Server;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ServerRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Server server) {
        em.persist(server);
    }

    public Server find(long id) {
        return em.find(Server.class, id);
    }

    public List<Server> findAll() {
        return em.createQuery("SELECT l FROM Server l", Server.class)
                .getResultList();
    }

    public List<Server> findByUser(long userId) {
        return em.createQuery("SELECT l FROM Server l, AccountServer ac" +
                " WHERE l.id = ac.serverId" +
                " AND ac.accountId = :id", Server.class)
                .setParameter("id", userId)
                .getResultList();
    }

    public void update(Server server) {
        em.merge(server);
    }

    public void detach(Server server) {
        em.detach(server);
    }

    public void delete(long id) {
        Server serverToRemove = em.find(Server.class, id);
        em.remove(serverToRemove);
    }
}
