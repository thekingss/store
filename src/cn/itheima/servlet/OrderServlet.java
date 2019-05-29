package cn.itheima.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itheima.bean.Cart;
import cn.itheima.bean.CartItem;
import cn.itheima.bean.Order;
import cn.itheima.bean.OrderItem;
import cn.itheima.bean.PageBean;
import cn.itheima.bean.user;
import cn.itheima.service.OrderService;
import cn.itheima.serviceimp.OrderServiceimp;
import cn.itheima.store.order.utils.PaymentUtil;
import cn.itheima.utils.BaseServlet;
import cn.itheima.utils.UUIDUtils;

public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String addOrder(HttpServletRequest request, HttpServletResponse response){
		try {
			HttpSession session = request.getSession();
			user user = (user) session.getAttribute("user");
			if (user == null) {
				request.setAttribute("msg", "亲,你要下单需要先登录");
				return "/jsp/info.jsp";
			}
			Order order = new Order();
			order.setOid(UUIDUtils.getUUID());
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			String date = f.format(new Date());
			order.setOrdertime(date);
			order.setState(0);
			Cart cart = (Cart) session.getAttribute("cart");
			order.setTotal(cart.getTotal());
			order.setUser(user);
			List<OrderItem> list = order.getList();
			Map<String, CartItem> map = cart.getMap();
			for (String key : map.keySet()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setItemid(UUIDUtils.getUUID());
				orderItem.setCount(map.get(key).getCount());
				orderItem.setSubtotal(map.get(key).getSubtotal());
				orderItem.setProduct(map.get(key).getProduct());
				orderItem.setOrder(order);

				list.add(orderItem);
			}
			OrderService os = new OrderServiceimp();
			os.save(order);
			cart.clear();
			request.setAttribute("order", order);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jsp/order_info.jsp";
	}
	
	public String findOrder(HttpServletRequest request, HttpServletResponse response){
		try {
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			int pageSize = 3;
			HttpSession session = request.getSession();
			user user = (user) session.getAttribute("user");
			OrderService os = new OrderServiceimp();
			PageBean pb = os.findOrder(user, pageNumber, pageSize);
			request.setAttribute("pb", pb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jsp/order_list.jsp";
	}
	
	public String findByoid(HttpServletRequest request, HttpServletResponse response){
		try {
			String oid = request.getParameter("oid");
			OrderService os = new OrderServiceimp();
			Order order = os.findByoid(oid);
			request.setAttribute("order", order);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jsp/order_info.jsp";
	}
	
	public String payOrder(HttpServletRequest request, HttpServletResponse response){
		try {
			String oid = request.getParameter("oid");
			OrderService os = new OrderServiceimp();
			Order order = os.findByoid(oid);
			String address = request.getParameter("address");
			String name = request.getParameter("name");
			String telephone = request.getParameter("telephone");
			order.setAddress(address);
			order.setName(name);
			order.setTelephone(telephone);
			os.update(order);
			
			// 付款 --第三方
			String p0_Cmd = "Buy";
			String p1_MerId = "10001126856";
			String p2_Order = order.getOid(); // 订单号
			String p3_Amt = "0.01";//测试用1分钱，真正开发中用order.getTotal();
			String p4_Cur = "CNY";
			String p5_Pid = "";
			String p6_Pcat = "";
			String p7_Pdesc = "";
			String p8_Url = "http://localhost:8888"+request.getContextPath()+"/order?method=callBack";
			String p9_SAF = "0";
			String pa_MP = "";
			String pd_FrpId = request.getParameter("pd_FrpId");
			String pr_NeedResponse = "1";
			// 电子签名
			String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl");
			
			
			StringBuffer buffer = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
			buffer.append("p0_Cmd="+p0_Cmd);
			buffer.append("&p1_MerId="+p1_MerId);
			buffer.append("&p2_Order="+p2_Order);
			buffer.append("&p3_Amt="+p3_Amt);
			buffer.append("&p4_Cur="+p4_Cur);
			buffer.append("&p5_Pid="+p5_Pid);
			buffer.append("&p6_Pcat="+p6_Pcat);
			buffer.append("&p7_Pdesc="+p7_Pdesc);
			buffer.append("&p8_Url="+p8_Url);
			buffer.append("&p9_SAF="+p9_SAF);
			buffer.append("&pa_MP="+pa_MP);
			buffer.append("&pd_FrpId="+pd_FrpId);
			buffer.append("&pr_NeedResponse="+pr_NeedResponse);
			buffer.append("&hmac="+hmac);
			
			response.sendRedirect(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String callBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获取支付的订单id
			String r6_Order = request.getParameter("r6_Order");
			// 修改状态为1 已付款
			OrderService orderService=new OrderServiceimp();
			Order order = orderService.findByoid(r6_Order);
			order.setState(1);
			// 修改
			orderService.update(order);
			
			// 给页面显示支付成功或则失败
			request.setAttribute("msg","恭喜你,支付成功,你为"+r6_Order+"支付了"+request.getParameter("r3_Amt")+"钱");
			
		} catch (Exception e) {
			
		}
		return "/jsp/info.jsp";
	}
}
