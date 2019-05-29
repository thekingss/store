package cn.itheima.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import cn.itheima.bean.user;
import cn.itheima.service.UserService;
import cn.itheima.serviceimp.UserServiceimp;
import cn.itheima.utils.BaseServlet;
import cn.itheima.utils.MailUtils;
import cn.itheima.utils.UUIDUtils;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	public String registerUI(HttpServletRequest request, HttpServletResponse response) {
		return "/jsp/register.jsp";
	}
	
	public String loginUI(HttpServletRequest request, HttpServletResponse response) {
		return "/jsp/login.jsp";
	}
	
	public String register(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, String[]> map = request.getParameterMap();
			user user = new user();
			BeanUtils.populate(user, map);
			user.setUid(UUIDUtils.getUUID());
			user.setState(0);
			user.setCode(UUIDUtils.getUUID() + UUIDUtils.getUUID());
			UserService us = new UserServiceimp();
			us.save(user);
			
			String email = user.getEmail();
			String emailMsg="这是一封激活邮件,请<a href=http://localhost:8888/store/user?method=active&code="+user.getCode()+">点击激活"+user.getCode()+"</a>";
			MailUtils.sendMail(email, emailMsg);
			request.setAttribute("msg", "亲,注册成功啦,赶快去激活吧..");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "注册失败,请稍后重试..");
			return "/jsp/info.jsp";
		}
		return "/jsp/info.jsp";
	}
	
	public String active(HttpServletRequest request, HttpServletResponse response) {
		try {
			String code = request.getParameter("code");
			UserService us = new UserServiceimp();
			user user = us.FindByCode(code);
			if(user==null)
			{
				// 没有  --做提示 激活时间已过期
				request.setAttribute("msg", "亲,激活失效已经过期了,需要你重新注册...");
				return "/jsp/info.jsp";
			}
			user.setState(1);
			us.update(user);
			request.setAttribute("msg", "亲,激活成功,请去登录吧...");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg","激活失败,请稍后重试..");
			return "/jsp/info.jsp";
		}
		return "/jsp/info.jsp";
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response) {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			UserService us = new UserServiceimp();
			user user = us.login(username, password);
			if (user == null) {
				request.setAttribute("msg", "用户名或者密码错误");
				return "/jsp/login.jsp";
			}
			if (user.getState() != 1) {
				request.setAttribute("msg", "亲,你还没有激活,请先去邮箱激活再来登录..");
				return "/jsp/info.jsp";
			}
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "登录失败....");
			return "/jsp/info.jsp";
		}
		return null;
	}
	
	public void quit(HttpServletRequest request, HttpServletResponse response) throws IOException {		
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		response.sendRedirect(request.getContextPath() + "/jsp/index.jsp");
	}
}
