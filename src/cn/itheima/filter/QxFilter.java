package cn.itheima.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.functors.ChainedClosure;

import cn.itheima.bean.user;

public class QxFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpSession session = request.getSession();
		user user = (user)session.getAttribute("user");
		if(user==null) {
			request.setAttribute("msg", "亲,你没有权限访问该资源,需要先登录");
			request.getRequestDispatcher("/jsp/info.jsp").forward(request, arg1);
			return;
		}
		arg2.doFilter(request, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
