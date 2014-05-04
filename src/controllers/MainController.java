package controllers;

import dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired TestDao testDao;

    @RequestMapping("index.html")
    public String mainPage() {
        System.out.println(testDao.GetParts());
        return "index";
    }
}
