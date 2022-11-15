package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author VvV
 * @date 2022/6/30
 */
@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "hello spring boot.";
    }

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/data")
    @ResponseBody
    public String viewData(){
        return alphaService.processData();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":" + value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html,charset=tf-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 参数获取方式1：students?current=1&limit=20
     * 指定get请求
     */
    @RequestMapping(path="/student", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){
        return "some Students.";
    }

    /**
     * 参数获取方式2：/student/123
     */
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable ("id") int id){
        return "a Student, id=" + id;
    }

    /**
     *参数名字与表单中一致，也会自动传过来，也可以使用@RequestParam
     * Post请求
     */
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String student(String name, String sex){
        System.out.println(name);
        System.out.println(sex);
        return "success";
    }

    /**
     *响应HTML数据  先将参数传到html，然后返回给浏览器
     * 不加 @ResponseBody注解 默认返回的是HTML
     * 两种方式：
     * 1.实例化一个ModelAndView，将model和view都设置好，返回给浏览器
     * 2.通过模板引擎，把model数据装到参数里，把view数据直接返回（model的引用被dispatcherServlet持有）
     */
    @RequestMapping("/teacher")
    public ModelAndView teacher(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","homo simpson");
        modelAndView.addObject("age",10);
        modelAndView.setViewName("demo/view");

        return modelAndView;
    }
    @RequestMapping("/school")
    public String getSchool(Model model){
        model.addAttribute("name","南京航空航天大学");
        model.addAttribute("age",70);

        return "demo/view";
    }


    /**
     *响应JSON数据（异步请求）
     * Java对象 -> Json字符串 -> JS对象
     */
    @RequestMapping(path = "employee", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> getEmployee(){
        Map<String, String> map = new HashMap<>(4);
        map.put("name","homo");
        map.put("age","10");

        return map;
    }

    @RequestMapping(path = "employees", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, String>> getEmployees(){
        List<Map<String, String>> mapList = new LinkedList<>();

        Map<String, String> map1 = new HashMap<>(4);
        map1.put("name","homo");
        map1.put("age","30");

        Map<String, String> map2 = new HashMap<>(4);
        map2.put("name","bart");
        map2.put("age","10");

        mapList.add(map1);
        mapList.add(map2);

        return mapList;
    }

    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        cookie.setPath("/community/alpha");
        //单位是秒 10min
        cookie.setMaxAge(60 * 10);
        //发送cookie
        response.addCookie(cookie);

        return "set cookie";
    }

    @RequestMapping(path = "/cookie/get", method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code){
        System.out.println(code);
        return "get cookie";
    }

    /**
     * 创建后，浏览器本地通过cookie保存session的Id
     * @param session
     * @return
     */
    @RequestMapping(path = "/session/set", method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpSession session){
        session.setAttribute("id",1);
        session.setAttribute("name","sessionTest");
        return "set session";
    }

    /**
     * session创建后，浏览器再次访问服务器，携带自己本地的cookie，cookie中有session的id
     * @param session
     * @return
     */
    @RequestMapping(path = "/session/get", method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(HttpSession session){
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }

}
