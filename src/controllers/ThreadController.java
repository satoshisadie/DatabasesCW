package controllers;

import beans.Post;
import beans.Thread;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import dao.PostDao;
import dao.ThreadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ThreadController {
    @Autowired ThreadDao threadDao;
    @Autowired PostDao postDao;

    @RequestMapping("newThread.html")
    public String newThread() {
        return "thread/newThread";
    }

    @RequestMapping("createThread.html")
    public String createThread(Thread thread) {
        threadDao.create(thread);
        return "index";
    }

//    @RequestMapping("viewThread.html")
//    public String viewThread(@RequestParam(value = "threadId", required = true) int threadId,
//                             HttpServletRequest request) {
//        request.setAttribute("threadId", threadId);
//        return "thread/viewThread";
//    }

//    @RequestMapping("updateThread.html")
//    public String viewThread(Thread thread) {
//        threadDao.update(thread);
//        return "index";
//    }

    @RequestMapping("thread.html")
    public String openThread(@RequestParam(value = "id", required = true) int threadId,
                             HttpServletRequest request) {
        Thread thread = threadDao.get(threadId);
        List<Post> posts = postDao.getThreadPosts(threadId);

        posts.sort((post1, post2) -> -post1.getDateCreated().compareTo(post2.getDateCreated()));

        Map<String, List<Post>> postsByParentId = new HashMap<>();

        for (Post post : posts) {
            String repliedTo = post.getRepliedTo().toString();
            if (!postsByParentId.containsKey(repliedTo)) {
                postsByParentId.put(repliedTo, new ArrayList<>());
            }
            postsByParentId.get(repliedTo).add(post);
        }
        request.setAttribute("thread", thread);
        request.setAttribute("posts", postsByParentId);
        return "thread/thread";
    }

    @RequestMapping("followThread")
    public  String followThread(@RequestParam(value = "threadId", required = true) int threadId,
                                HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        threadDao.follow(userId, threadId);
        return "index";
    }
}
