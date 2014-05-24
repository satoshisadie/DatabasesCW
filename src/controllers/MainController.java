package controllers;

import beans.*;
import dao.CommonDao;
import dao.ForumDao;
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

@Controller
public class MainController {
    @Autowired ThreadDao threadDao;
    @Autowired UserDao userDao;
    @Autowired CommonDao commonDao;
    @Autowired ForumDao forumDao;

    @RequestMapping("forums.html")
    public String viewForums(HttpServletRequest request) {
        List<Forum> forums = forumDao.getTopForums();
        request.setAttribute("forums", forums);
        return "forums";
    }

    // TODO IMPLEMENT
    @RequestMapping("administrator.html")
    public String administratorPage(HttpServletRequest request) {
        return "administrator";
    }

    // TODO IMPLEMENT
    @RequestMapping("moderator.html")
    public String moderatorPage(HttpServletRequest request) {
        return "moderator";
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
        httpServletResponse.sendRedirect("forums.html");
    }

    @ResponseBody
    @RequestMapping("logout.html")
    public String logout(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        if (session != null) session.invalidate();
        return "success";
    }
}
