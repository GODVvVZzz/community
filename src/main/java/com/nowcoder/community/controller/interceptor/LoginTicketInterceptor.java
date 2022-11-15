package com.nowcoder.community.controller.interceptor;

import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CookieUtil;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author VvV
 * @date 2022/7/14
 */
@Component
public class LoginTicketInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    /**
     * 每次请求时 根据登录凭证ticket查询当前是否登录
     * 初始的时候将用户暂存到当前线程中，当前线程在本次请求中一直是存活的
     * 不使用@CookieValue注解 是因为 这里是重写方法 参数不能随意修改
     * @param request
     * @param response
     * @param handler
     * @return true 表示后面两个方法才执行 false不执行
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 从Cookie中获取凭证
        String ticket = CookieUtil.getValue(request,"ticket");

        if (ticket != null){
            LoginTicket loginTicket = userService.findByTicket(ticket);
            // 检查凭证是否有效 别忘记超时时间
            if (loginTicket != null && loginTicket.getStatus() == 0 && loginTicket.getExpired().after(new Date())){
                // 根据凭证查询用户
                User user = userService.findUserById(loginTicket.getUserId());
                // hostHolder 存的是用户信息
                // 在本次请求中持有用户
                hostHolder.setUser(user);
                // SecurityContext存的是认证结果
                // 构建用户认证的结果
                Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(),
                        userService.getAuthorities(user.getId()));
                // 将认证结果存入到SecurityContext
                SecurityContextHolder.setContext(new SecurityContextImpl(authentication));
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = hostHolder.getUser();
        if (user != null && modelAndView != null){
            modelAndView.addObject("loginUser", user);
        }

    }

    // 本次请求结束 清理线程中的数据
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.remove();
        SecurityContextHolder.clearContext();
    }

}
