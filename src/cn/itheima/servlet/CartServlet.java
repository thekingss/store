package cn.itheima.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itheima.bean.Cart;
import cn.itheima.bean.CartItem;
import cn.itheima.bean.Product;
import cn.itheima.service.productService;
import cn.itheima.serviceimp.productServiceimp;
import cn.itheima.utils.BaseServlet;


public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	public Cart getCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		if(cart==null) {
			cart=new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
	
	public String addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String pid = request.getParameter("pid");
			
			productService ps = new productServiceimp();
			
			Product product = ps.findByPid(pid);
		
			int count = Integer.parseInt(request.getParameter("count"));
			
			CartItem item = new CartItem();
			
			item.setCount(count);
			item.setProduct(product);
			
			Cart cart = getCart(request);
			
			cart.add(item);
			
			request.setAttribute("cart", cart);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/jsp/cart.jsp";
	}
	
	public void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		
		Cart cart = getCart(request);
		cart.remove(pid);
		
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
	}
	
	public void removeAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Cart cart = getCart(request);
		cart.clear();
		
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
	}
}
