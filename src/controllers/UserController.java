package controllers;

import beans.User;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired UserDao userDao;

    @RequestMapping("createUser.html")
    public String userRegistration(User user) {
        userDao.createUser(user);
        return "authorization";
    }

    @RequestMapping("registration.html")
    public String userRegistration() {
        return "user/newUser";
    }

    @RequestMapping("viewProfile.html")
    public String viewProfile() {
        return "user/viewProfile";
    }
}
