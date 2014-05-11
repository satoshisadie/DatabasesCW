package controllers;

import beans.Post;
import beans.Thread;
import beans.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import dao.CommonDao;
import dao.PostDao;
import dao.ThreadDao;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class ThreadController {
    @Autowired ThreadDao threadDao;
    @Autowired PostDao postDao;
    @Autowired UserDao userDao;
    @Autowired CommonDao commonDao;
    private Gson serializer = new Gson();

    @ResponseBody
    @RequestMapping("createThread.html")
    public String createThread(@RequestParam(value = "subject", required = true) String threadSubject,
                               @RequestParam(value = "initialPost", required = true) String initialPost,
                               @RequestParam(value = "tags", required = true) String tagsJson,
                               HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute("userId");

        Thread thread = new Thread();
        thread.setSubject(threadSubject);
        thread.setUserId(userId);
        int threadId = threadDao.create(thread);

        List<Integer> tagsIds = serializer.fromJson(tagsJson, new TypeToken<List<Integer>>(){}.getType());
        commonDao.attachTags(threadId, tagsIds);

        Post post = new Post();
        post.setMessage(initialPost);
        post.setThreadId(threadId);
        post.setUserId(userId);
        postDao.create(post);

        return "success";
    }

    @RequestMapping("thread.html")
    public String openThread(@RequestParam(value = "id", required = true) int threadId,
                             HttpServletRequest request) {
        Thread thread = threadDao.get(threadId);

        thread.setViewCount(thread.getViewCount() + 1);
        threadDao.update(thread);

        List<Post> posts = postDao.getThreadPosts(threadId);
        posts.sort((post1, post2) -> post1.getDateCreated().compareTo(post2.getDateCreated()));

        Set<Integer> usersIds = Sets.newTreeSet(Lists.transform(posts, Post::getUserId));
        List<User> users = userDao.getAll(usersIds);
        Map<Integer,User> userById = Maps.uniqueIndex(users, User::getId);

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
        request.setAttribute("userById", userById);
        return "thread/thread";
    }

    // TODO end this part
    @RequestMapping("followThread")
    public  String followThread(@RequestParam(value = "threadId", required = true) int threadId,
                                HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        threadDao.follow(userId, threadId);
        return "index";
    }
}
