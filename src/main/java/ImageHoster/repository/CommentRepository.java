package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;


@Repository
public class CommentRepository {

    // persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    //The method creates an instance of EntityManager
    //Returns the list of all the comments database
    public List<Comment> commentsByImageID(Integer imageId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Comment> comments = em.createQuery("SELECT c FROM Comment c WHERE c.image_id = :imageId", Comment.class);
        comments.setParameter("imageId", imageId);

        return comments.getResultList();
    }

        //Creates an instance of EntityManager
        //Starts a transaction
        //receives the Comment object to store in the database

        public Comment setComments(Comment comment) {
            EntityManager em = emf.createEntityManager();
            EntityTransaction transaction = em.getTransaction();

            try {
                transaction.begin();
                em.persist(comment);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
            return comment;
        }
    }
