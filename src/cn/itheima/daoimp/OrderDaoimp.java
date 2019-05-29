package cn.itheima.daoimp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itheima.bean.Order;
import cn.itheima.bean.OrderItem;
import cn.itheima.bean.Product;
import cn.itheima.dao.OrderDao;
import cn.itheima.utils.DataSourceUtils;

public class OrderDaoimp implements OrderDao {

	public void save(Order order, Connection con) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		qr.update(con, sql, order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid());
	}

	public void saveOrderItem(OrderItem orderItem, Connection con) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "insert into orderitem values(?,?,?,?,?)";
		qr.update(con, sql, orderItem.getItemid(),orderItem.getCount(),orderItem.getSubtotal(),orderItem.getProduct().getPid(),orderItem.getOrder().getOid());
	}

	public List<Order> findOrder(String uid, int i, int pageSize) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where uid=? limit ?,?";
		List<Order> list = qr.query(sql, new BeanListHandler<Order>(Order.class), uid,i,pageSize);
		for(Order order : list) {
			List<OrderItem> list2 = order.getList();
			String sql1 = "SELECT * FROM orderitem o,product p WHERE o.oid=? AND o.pid=p.pid";
			List<Map<String, Object>> mlist = qr.query(sql1, new MapListHandler(), order.getOid());
			for(Map<String, Object> map : mlist) {
				OrderItem orderItem = new OrderItem();
				BeanUtils.populate(orderItem, map);
				Product product = new Product();
				BeanUtils.populate(product, map);
				orderItem.setProduct(product);
				list2.add(orderItem);
			}
		}
		return list;
	}

	public int findCount(String uid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from orders where uid=?";
		Long l = (Long)qr.query(sql, new ScalarHandler(), uid);
		return l.intValue();
	}

	public Order findByoid(String oid) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM orders WHERE oid=?";
		Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
		
		if(order!=null) {
			List<OrderItem> list = order.getList();
			String sql2 = "SELECT * FROM orderitem o,product p WHERE o.oid=? AND o.pid=p.pid";
			List<Map<String, Object>> mlist = qr.query(sql2, new MapListHandler(), order.getOid());
			for(Map<String,Object> map : mlist) {
				OrderItem orderItem = new OrderItem();
				BeanUtils.populate(orderItem, map);
				Product product = new Product();
				BeanUtils.populate(product, map);
				orderItem.setProduct(product);
				
				list.add(orderItem);
			}
		}
		return order;
	}

	public void update(Order order) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="update orders set state=?,address=?,name=?,telephone=? where oid=?";
		qr.update(sql, order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
	}

}
