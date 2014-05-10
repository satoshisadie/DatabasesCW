package controllers;

import beans.Thread;
import beans.User;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import dao.ThreadDao;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Controller
public class MainController {
    @Autowired ThreadDao threadDao;
    @Autowired UserDao userDao;

    @RequestMapping("index.html")
    public String mainPage(HttpServletRequest request) {
        List<Thread> threads = threadDao.getAll();
        threads.sort((thread1, thread2) -> -thread1.getDateLastPost().compareTo(thread2.getDateLastPost()));

        Set<Integer> usersIds = Sets.newTreeSet(Lists.transform(threads, Thread::getUserId));
        List<User> users = userDao.getAll(usersIds);
        Map<Thread, User> userByThread = Maps.toMap(threads, thread -> Iterables.find(users, user -> user.getId().equals(thread.getUserId())));



        request.setAttribute("threads", threads);
        request.setAttribute("userByThread", userByThread);
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
