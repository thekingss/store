package cn.itheima.serviceimp;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.List;

import cn.itheima.bean.Order;
import cn.itheima.bean.OrderItem;
import cn.itheima.bean.PageBean;
import cn.itheima.bean.user;
import cn.itheima.dao.OrderDao;
import cn.itheima.daoimp.OrderDaoimp;
import cn.itheima.service.OrderService;
import cn.itheima.utils.DataSourceUtils;

public class OrderServiceimp implements OrderService {

	public void save(Order order) throws SQLException {
		Connection con=null;
		try {
			OrderDao od = new OrderDaoimp();
			//¿ªÆôÊÂÎñ
			con = DataSourceUtils.getConnection();
			con.setAutoCommit(false);
			od.save(order, con);
			
			List<OrderItem> list = order.getList();
			for (OrderItem orderItem : list) {
				od.saveOrderItem(orderItem, con);
			} 
			con.commit();
		} catch (Exception e) {
			System.out.println(111);
			con.rollback();
		}
	}

	public PageBean findOrder(user user, int pageNumber, int pageSize) throws Exception{
		OrderDao od = new OrderDaoimp();
		List<Order> list = od.findOrder(user.getUid(),(pageNumber-1)*pageSize,pageSize);
		int count = od.findCount(user.getUid());
		int page = 0;
		if(count%pageSize==0) {
			page = count/pageSize;
		}else {
			page = count/pageSize+1;
		}
		
		PageBean pb = new PageBean();
		pb.setList(list);
		pb.setPageNumber(pageNumber);
		pb.setPageSize(pageSize);
		pb.setTotalCount(count);
		pb.setTotalPage(page);
		
		return pb;
	}

	public Order findByoid(String oid) throws Exception {
		OrderDao od = new OrderDaoimp();
		return od.findByoid(oid);
	}

	public void update(Order order) throws SQLException {
		OrderDao od = new OrderDaoimp();
		od.update(order);
	}

}
