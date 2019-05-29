package cn.itheima.service;

import java.sql.SQLException;
import java.util.List;

import cn.itheima.bean.PageBean;
import cn.itheima.bean.Product;

public interface productService {

	PageBean findProduct(String cid, int pageNumber, int pageSize) throws SQLException;

	Product findByPid(String pid) throws SQLException;

	List<Product> hotList() throws SQLException;

}
