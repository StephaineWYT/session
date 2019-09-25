package wen.session.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wen.session.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/session")
public class SessionController {

    /**
     * 获取页面的请求地址，并把请求地址放入到 Session 中，并将结果返回
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/setSession", method = RequestMethod.GET)
    public Map<String, Object> setSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("requestUrl", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return map;
    }

    /**
     * 获取页面的请求地址，并把请求地址放⼊入 Key 为 message 的 Session 中，并将结果返回
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/setSessionKey")
    public Map<String, Object> setSessionKey(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("message", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return map;
    }


    /**
     * 获取 Session 中的请求中的 Session Id 和 Key 为 message 的信息封装返回
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getSession")
    public Object getSession(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("message"));
        return map;
    }

    /**
     * 登录成功后将用户信息存放到 Session 中
     *
     * @param request
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, String username, String password) {
        String message = "login failure!";
        if (username != null && password != null) {
            User user = new User(username, password);
            request.getSession().setAttribute("user", user);
            message = "login successfully!";
        }
        return message;
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        String message = "8080 restrained content";
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            message = "please login first!";
        }
        return message;
    }

}
