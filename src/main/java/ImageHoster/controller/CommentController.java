package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;
    //Controller handles request pattern of type '/image/{imageId}/{imageTitle}/comments'
   //Accepts all comments and stores in Databases and redirects to the '/image/{imageId}/{imageTitle}'

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
       public String setComments(@PathVariable("imageId") Integer imageId, @RequestParam("comment") String comment, Comment newComment, HttpSession session, @PathVariable String imageTitle) throws IOException {

           User user = (User) session.getAttribute("loggeduser");
           Image image = imageService.getImage(imageId);

           newComment.setUser(user);
           newComment.setImage(image);
           newComment.setCreatedDate(new Date());
           newComment.setText(comment);

           commentService.setComments(newComment);
           return "redirect:/images/" + imageId + "/" + image.getTitle();
       }
   }
