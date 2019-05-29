package cn.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import cn.itheima.bean.Product;

public interface productDao {

	int findCount(String cid) throws SQLException;

	List<Product> findList(String cid, int i, int pageSize) throws SQLException;

	Product findByPid(String pid) throws SQLException;

	List<Product> hotList() throws SQLException;

}
