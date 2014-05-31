package controllers;

import beans.*;
import beans.Topic;
import com.google.common.collect.*;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import dao.CommonDao;
import dao.PostDao;
import dao.TopicDao;
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
public class TopicController {
    @Autowired
    TopicDao topicDao;
    @Autowired PostDao postDao;
    @Autowired UserDao userDao;
    @Autowired CommonDao commonDao;
    private Gson serializer = new Gson();

    @ResponseBody
    @RequestMapping("createTopic.html")
    public String createTopic(@RequestParam(value = "forumId", required = true) Integer forumId,
                              @RequestParam(value = "subject", required = true) String topicSubject,
                              @RequestParam(value = "initialPost", required = true) String initialPost,
                              @RequestParam(value = "tags", required = true) String tagsJson,
                              HttpServletRequest request) {
        int userId = (int) request.getSession().getAttribute("userId");

        Topic topic = new Topic();
        topic.setSubject(topicSubject);
        topic.setUserId(userId);
        topic.setForumId(forumId);
        int topicId = topicDao.create(topic);

        List<Integer> tagsIds = serializer.fromJson(tagsJson, new TypeToken<List<Integer>>(){}.getType());
        commonDao.attachTags(topicId, tagsIds);

        Post post = new Post();
        post.setMessage(initialPost);
        post.setTopicId(topicId);
        post.setUserId(userId);
        postDao.create(post);

        return "success";
    }

    @RequestMapping("viewTopic.html")
    public String viewTopic(@RequestParam(value = "id", required = true) int topicId,
                             HttpServletRequest request) {
        Topic topic = topicDao.get(topicId);
        request.setAttribute("topic", topic);

        // Increment view count by 1
        topic.setViewCount(topic.getViewCount() + 1);
        topicDao.update(topic);

        List<Tag> tags = commonDao.getTagsByTopic(topicId);
        request.setAttribute("tags", tags);

        List<Post> posts = postDao.getTopicPosts(topicId);
        posts.sort((post1, post2) -> post1.getDateCreated().compareTo(post2.getDateCreated()));

        Set<Integer> usersIds = Sets.newTreeSet(Lists.transform(posts, Post::getUserId));
        List<User> users = userDao.getUsersByIds(usersIds);
        Map<Integer,User> userById = Maps.uniqueIndex(users, User::getId);
        request.setAttribute("userById", userById);

        Multimap<String, Post> postsByParentId = ArrayListMultimap.create();
        for (Post post : posts) {
            postsByParentId.put(post.getRepliedTo().toString(), post);
        }
        request.setAttribute("posts", postsByParentId.asMap());

        List<ForumRule> forumRules = commonDao.getAllRules();
        request.setAttribute("forumRules", forumRules);

        return "topic/viewTopic";
    }

    @ResponseBody
    @RequestMapping("followTopic.html")
    public  String followThread(@RequestParam(value = "topicId", required = true) int threadId,
                                HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        topicDao.follow(userId, threadId);
        return "success";
    }

    @ResponseBody
    @RequestMapping("cancelFollowing.html")
    public  String cancelFollowing(@RequestParam(value = "topicId", required = true) int threadId,
                                   HttpServletRequest request) {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        topicDao.cancelFollowing(userId, threadId);
        return "success";
    }
}
