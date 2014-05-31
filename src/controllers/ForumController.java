package controllers;

import beans.*;
import beans.Topic;
import com.google.common.collect.*;
import dao.CommonDao;
import dao.ForumDao;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class ForumController {
    @Autowired ForumDao forumDao;
    @Autowired CommonDao commonDao;
    @Autowired UserDao userDao;

    @RequestMapping("viewForum.html")
    public String viewForum(@RequestParam(value = "id") Integer forumId,
                            HttpServletRequest request) {
        request.setAttribute("forumId", forumId);

        List<Topic> topics = forumDao.getTopics(forumId);
        // Sort topics from last date to earliest date
        topics.sort((topic1, topic2) -> -topic1.getDateLastPost().compareTo(topic2.getDateLastPost()));
        request.setAttribute("topics", topics);

        // If topics not empty get users and tags for this topics
        if (topics.size() > 0) {
            Set<Integer> usersIds = Sets.newTreeSet(Lists.transform(topics, Topic::getUserId));
            List<User> users = userDao.getUsersByIds(usersIds);
            Map<Topic, User> userByTopic = Maps.toMap(topics, topic -> Iterables.find(users, user -> user.getId().equals(topic.getUserId())));
            request.setAttribute("userByTopic", userByTopic);

            List<TopicTag> topicTags = commonDao.getTopicTagForTopics(Lists.transform(topics, Topic::getId));
            Multimap<Integer, Integer> tagsByTopicId = ArrayListMultimap.create();
            for (TopicTag topicTag : topicTags) {
                tagsByTopicId.put(topicTag.getTopicId(), topicTag.getTagId());
            }
            request.setAttribute("tagsByTopicId", tagsByTopicId.asMap());
        }

        List<Forum> subforums = forumDao.getSubforums(forumId);
        request.setAttribute("subforums", subforums);

        List<Tag> tags = commonDao.getAllTags();
        request.setAttribute("tags", tags);

        Map<Integer, Tag> tagById = Maps.uniqueIndex(tags, Tag::getId);
        request.setAttribute("tagById", tagById);

        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        List<TopicFollower> topicFollowers = commonDao.getTopicFollowersByUserId(userId);
        Set<Integer> followedTopics = Sets.newHashSet(Lists.transform(topicFollowers, TopicFollower::getTopicId));
        request.setAttribute("followedTopics", followedTopics);

        return "viewForum";
    }
}
