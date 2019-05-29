package cn.itheima.daoimp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itheima.bean.Product;
import cn.itheima.dao.productDao;
import cn.itheima.utils.DataSourceUtils;

public class productDaoimp implements productDao {

	public int findCount(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product where cid=?";
		Long l = (Long)qr.query(sql, new ScalarHandler(),cid);
		return l.intValue();
	}

	public List<Product> findList(String cid, int i, int pageSize) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "select * from product where cid=? limit ?,?";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class), cid,i,pageSize);
		return list;
	}

	public Product findByPid(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "select * from product where pid=?";
		
		return qr.query(sql, new BeanHandler<Product>(Product.class), pid);
	}

	public List<Product> hotList() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where is_hot=1";
		
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

}
