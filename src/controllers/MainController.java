package controllers;

import beans.Thread;
import beans.User;
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

@Controller
public class MainController {
    @Autowired ThreadDao threadDao;
    @Autowired UserDao userDao;

    @RequestMapping("index.html")
    public String mainPage(HttpServletRequest request) {
        List<Thread> threads = threadDao.getAll();
        request.setAttribute("threads", threads);
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
        }

        httpServletResponse.setStatus(HttpServletResponse.SC_FOUND);
        httpServletResponse.sendRedirect("index.html");
    }
}
