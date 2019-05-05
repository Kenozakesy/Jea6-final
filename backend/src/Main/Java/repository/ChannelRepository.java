package repository;

import domain.channel.Channel;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ChannelRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Channel channel) {
        em.persist(channel);
    }

    public Channel find(long id) {
        return em.find(Channel.class, id);
    }

    public List<Channel> findAll() {
        return em.createQuery("SELECT l FROM Channel l", Channel.class)
                .getResultList();
    }

    public List<Channel> findByServerId(long serverId) {
        return em.createQuery("SELECT c FROM Server l, Channel c" +
                " WHERE l.id = c.serverId" +
                " AND l.id = :id", Channel.class)
                .setParameter("id", serverId)
                .getResultList();
    }

    public void update(Channel channel) {
        em.merge(channel);
    }

    public void detach(Channel channel) {
        em.detach(channel);
    }

    public void delete(long id) {
        Channel channelToRemove = em.find(Channel.class, id);
        em.remove(channelToRemove);
    }
}
