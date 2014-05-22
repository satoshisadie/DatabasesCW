package controllers;

import beans.*;
import beans.Thread;
import com.google.common.collect.*;
import dao.CommonDao;
import dao.ThreadDao;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class MainController {
    @Autowired ThreadDao threadDao;
    @Autowired UserDao userDao;
    @Autowired CommonDao commonDao;

    @RequestMapping("index.html")
    public String mainPage(HttpServletRequest request) {
        List<Thread> threads = threadDao.getAll();

        threads.sort((thread1, thread2) -> -thread1.getDateLastPost().compareTo(thread2.getDateLastPost()));
        request.setAttribute("threads", threads);

        if (threads.size() > 0) {
            Set<Integer> usersIds = Sets.newTreeSet(Lists.transform(threads, Thread::getUserId));
            List<User> users = userDao.getAll(usersIds);
            Map<Thread, User> userByThread = Maps.toMap(threads, thread -> Iterables.find(users, user -> user.getId().equals(thread.getUserId())));
            request.setAttribute("userByThread", userByThread);

            List<ThreadTag> threadTags = commonDao.getThreadTagForThreads(Lists.transform(threads, Thread::getId));
            Multimap<Integer, Integer> tagsByThreadId = ArrayListMultimap.create();
            for (ThreadTag threadTag : threadTags) {
                tagsByThreadId.put(threadTag.getThreadId(), threadTag.getTagId());
            }
            request.setAttribute("tagsByThreadId", tagsByThreadId.asMap());
        }

        List<Tag> tags = commonDao.getAllTags();
        request.setAttribute("tags", tags);

        Map<Integer, Tag> tagById = Maps.uniqueIndex(tags, Tag::getId);
        request.setAttribute("tagById", tagById);

        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        List<ThreadFollower> threadFollowers = commonDao.getThreadFollowersByUserId(userId);
        Set<Integer> followedThreads = Sets.newHashSet(Lists.transform(threadFollowers, ThreadFollower::getThreadId));
        request.setAttribute("followedThreads", followedThreads);

        return "index";
    }

    @RequestMapping("rules.html")
    public String forumRules(HttpServletRequest request) {
        List<ForumRule> rules = commonDao.getAllRules();
        request.setAttribute("rules", rules);
        return "index";
    }

    @RequestMapping("authorization.html")
    public String authorizationPage() {
        return "authorization";
    }

    @RequestMapping("authorize.html")
    public void authorize(HttpServletRequest request,
                          HttpServletResponse httpServletResponse) throws IOException {
        User user = userDao.get(request.getParameter("login"), request.getParameter("password"));

        if (user == null) {
            httpServletResponse.setStatus(HttpServletResponse.SC_FOUND);
            httpServletResponse.sendRedirect("authorization.html");
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("authorized", true);
        session.setAttribute("userId", user.getId());
        session.setAttribute("login", user.getLogin());

        if (user.getRole().equals("Administrator")) session.setAttribute("administrator", true);

        httpServletResponse.setStatus(HttpServletResponse.SC_FOUND);
        httpServletResponse.sendRedirect("index.html");
    }

    @ResponseBody
    @RequestMapping("logout.html")
    public String logout(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        if (session != null) session.invalidate();
        return "success";
    }
}
