package controllers;

import beans.Thread;
import beans.User;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import dao.ThreadDao;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired ThreadDao threadDao;
    @Autowired UserDao userDao;

    @RequestMapping("index.html")
    public String mainPage(HttpServletRequest request) {
        List<Thread> threads = threadDao.getAll();
        List<Integer> usersIds = Lists.transform(threads, Thread::getUserId);
        List<User> users = userDao.get(usersIds);
        Map<Thread, User> userByThread = Maps.toMap(threads, thread -> Iterables.find(users, user -> user.getId().equals(thread.getId())));

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
        HttpSession session = request.getSession();
        User user = userDao.get(request.getParameter("login"), request.getParameter("password"));

        if (user != null) {
            session.setAttribute("authorized", true);
            session.setAttribute("userId", user.getId());
            session.setAttribute("login", user.getLogin());
        }

        httpServletResponse.setStatus(HttpServletResponse.SC_FOUND);
        httpServletResponse.sendRedirect("index.html");
    }
}
