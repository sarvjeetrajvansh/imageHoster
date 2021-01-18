package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    //It obtain a List of all the comments in the database
    public List<Comment> commentByImageID(Integer imageId) {
        return commentRepository.commentByImageID(imageId);
    }

    //It passes the comment to be persisted in the database
    public Comment setComments(Comment comment) {
        return commentRepository.setComments(comment);
    }
}
