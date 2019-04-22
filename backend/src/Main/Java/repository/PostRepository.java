package repository;

import domain.post.Post;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PostRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }

    public Post find(long id) {
        return em.find(Post.class, id);
    }

    public List<Post> findAll() {
        return em.createQuery("SELECT l FROM Post l", Post.class)
                .getResultList();
    }

    public void update(Post post) {
        em.merge(post);
    }

    public void detach(Post post) {
        em.detach(post);
    }

    public void delete(long id) {
        Post postToRemove = em.find(Post.class, id);
        em.remove(postToRemove);
    }
}
