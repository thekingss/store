package cn.itheima.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itheima.bean.PageBean;
import cn.itheima.bean.Product;
import cn.itheima.service.productService;
import cn.itheima.serviceimp.productServiceimp;
import cn.itheima.utils.BaseServlet;

public class productServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String findProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String cid = request.getParameter("cid");
			
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			
			int pageSize = 12;
			
			productService ps = new productServiceimp();
			PageBean pb = ps.findProduct(cid, pageNumber, pageSize);
			request.setAttribute("cid", cid);
			request.setAttribute("pb", pb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jsp/product_list.jsp";
	}
	
	public String findByPid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String pid = request.getParameter("pid");
			productService ps = new productServiceimp();
			Product product = ps.findByPid(pid);
			request.setAttribute("product", product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jsp/product_info.jsp";
	}
	
	public String hotList(HttpServletRequest request, HttpServletResponse response) {
		try {
			productService ps = new productServiceimp();
			List<Product> hotlist = ps.hotList();
			request.setAttribute("hotList", hotlist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/jsp/index.jsp";
	}
}
