package controllers;

import beans.Post;
import dao.CommonDao;
import dao.PostDao;
import dao.ThreadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PostController {
    @Autowired PostDao postDao;
    @Autowired ThreadDao threadDao;
    @Autowired CommonDao commonDao;

    @ResponseBody
    @RequestMapping("createPost.html")
    public String createPost(@RequestParam(value = "threadId", required = true) Integer threadId,
                             @RequestParam(value = "postId", required = false) Integer postId,
                             @RequestParam(value = "message", required = true) String message,
                             HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute("userId");

        Post post = new Post();
        post.setThreadId(threadId);
        post.setMessage(message);
        post.setRepliedTo(postId);
        post.setUserId(userId);
        postDao.create(post);

        return "success";
    }

    @ResponseBody
    @RequestMapping("complainPost.html")
    public String complainPost(@RequestParam(value = "postId", required = true) Integer postId,
                               @RequestParam(value = "ruleId", required = true) Integer ruleId,
                               @RequestParam(value = "violationComment", required = false) String violationComment) {
        commonDao.attachViolation(postId, ruleId, violationComment);
        return "success";
    }
}
