package controllers;

import beans.*;
import beans.Thread;
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
        List<Thread> threads = forumDao.getThreads(forumId);
        // Sort threads from last date to earliest date
        threads.sort((thread1, thread2) -> -thread1.getDateLastPost().compareTo(thread2.getDateLastPost()));
        request.setAttribute("threads", threads);

        // If threads not empty get users and tags for this threads
        if (threads.size() > 0) {
            Set<Integer> usersIds = Sets.newTreeSet(Lists.transform(threads, Thread::getUserId));
            List<User> users = userDao.getUsersByIds(usersIds);
            Map<Thread, User> userByThread = Maps.toMap(threads, thread -> Iterables.find(users, user -> user.getId().equals(thread.getUserId())));
            request.setAttribute("userByThread", userByThread);

            List<ThreadTag> threadTags = commonDao.getThreadTagForThreads(Lists.transform(threads, Thread::getId));
            Multimap<Integer, Integer> tagsByThreadId = ArrayListMultimap.create();
            for (ThreadTag threadTag : threadTags) {
                tagsByThreadId.put(threadTag.getThreadId(), threadTag.getTagId());
            }
            request.setAttribute("tagsByThreadId", tagsByThreadId.asMap());
        }

        List<Forum> subforums = forumDao.getSubforums(forumId);
        request.setAttribute("subforums", subforums);

        List<Tag> tags = commonDao.getAllTags();
        request.setAttribute("tags", tags);

        Map<Integer, Tag> tagById = Maps.uniqueIndex(tags, Tag::getId);
        request.setAttribute("tagById", tagById);

        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        List<ThreadFollower> threadFollowers = commonDao.getThreadFollowersByUserId(userId);
        Set<Integer> followedThreads = Sets.newHashSet(Lists.transform(threadFollowers, ThreadFollower::getThreadId));
        request.setAttribute("followedThreads", followedThreads);

        return "viewForum";
    }
}
