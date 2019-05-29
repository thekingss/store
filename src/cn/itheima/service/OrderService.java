package cn.itheima.service;

import java.sql.SQLException;

import cn.itheima.bean.Order;
import cn.itheima.bean.PageBean;
import cn.itheima.bean.user;

public interface OrderService {

	void save(Order order) throws SQLException;

	PageBean findOrder(user user, int pageNumber, int pageSize) throws Exception;

	Order findByoid(String oid) throws Exception;

	void update(Order order) throws SQLException;

}
