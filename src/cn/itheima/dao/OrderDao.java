package cn.itheima.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.itheima.bean.Order;
import cn.itheima.bean.OrderItem;

public interface OrderDao {

	void save(Order order, Connection con) throws SQLException;

	void saveOrderItem(OrderItem orderItem, Connection con) throws SQLException;

	List<Order> findOrder(String uid, int i, int pageSize) throws Exception;

	int findCount(String uid) throws SQLException;

	Order findByoid(String oid) throws Exception;

	void update(Order order) throws SQLException;

}
