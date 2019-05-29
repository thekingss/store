package cn.itheima.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;


public class MyFilter implements Filter
{

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Ҫ��ǿ�ķ���:request.getParameter
		// ������Ķ���: request
		
		final HttpServletRequest request=(HttpServletRequest)req;
		
		// ��̬�����ɴ������
		HttpServletRequest hsr=(HttpServletRequest)Proxy.newProxyInstance(
				request.getClass().getClassLoader(), 
				request.getClass().getInterfaces(),
				new InvocationHandler() {
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						
						// 1 �ж��Ƿ�����Ҫ��ǿ�ķ��� getParameter
						if("getParameter".equals(method.getName()))
						{
							// ֪��getParameterʹ�õ����ĸ��ύ��ʽ 
							String m = request.getMethod();
							// �ж���get����post
							if("get".equalsIgnoreCase(m))
							{
								// ��ǰ�������ú������
								String s =(String)method.invoke(request, args);
								// ��ǿ--�������
								s=new String(s.getBytes("iso8859-1"),"utf-8");
								return s;
							}
							
							if("post".equalsIgnoreCase(m))
							{
								request.setCharacterEncoding("utf-8");
								
							}
						}
						
						// ����Ǳ�ķ���
						return method.invoke(request, args);
					}
				});
		
		// ����
		chain.doFilter(hsr, response);
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
