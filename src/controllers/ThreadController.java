package controllers;

import beans.Thread;
import dao.ThreadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ThreadController {
    @Autowired ThreadDao threadDao;

    @RequestMapping("newThread.html")
    public String newThread() {
        return "thread/newThread";
    }

    @RequestMapping("createThread.html")
    public String createThread(Thread thread) {

        return "index";
    }

    @RequestMapping("viewThread.html")
    public String viewThread(@RequestParam(value = "id", required = true) int id,
                             HttpServletRequest request) {
        request.setAttribute("id", id);

        return "thread/viewThread";
    }

    @RequestMapping("updateThread.html")
    public String viewThread(Thread thread) {
        threadDao.update(thread);
        return "index";
    }
}
