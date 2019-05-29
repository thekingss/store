package cn.itheima.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itheima.service.categroyService;
import cn.itheima.serviceimp.categroyServiceimp;
import cn.itheima.utils.BaseServlet;

public class categroyServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String findCategroy(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		try {
			categroyService cs = new categroyServiceimp();
			String json = cs.findCategroy();
			response.getWriter().print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
