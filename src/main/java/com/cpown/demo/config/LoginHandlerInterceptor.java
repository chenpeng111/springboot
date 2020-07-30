package com.cpown.demo.config;

import com.cpown.demo.pojo.SysUser;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器（使用shiro了 可以抛弃这个）
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    /**
     * 方法执行之前进行拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SysUser loginUser = (SysUser)request.getSession().getAttribute("loginUser");
        if(null == loginUser){
            System.out.println("没有登录：loginUser");
            request.setAttribute("msg","用户没有登录无法访问");
            //此处重定向地址  一定不能够被拦截 否则会陷入死循环
            request.getRequestDispatcher("/index.html").forward(request,response);
            //使用 重定向提交response以后  请求就结束了，必须返回false，如果返回true 会报错
            return false;
        }
        return true;
    }
}
