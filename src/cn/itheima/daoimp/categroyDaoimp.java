package cn.itheima.daoimp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itheima.bean.CateGory;
import cn.itheima.dao.categroyDao;
import cn.itheima.utils.DataSourceUtils;

public class categroyDaoimp implements categroyDao {

	public List<CateGory> findCategroy() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "select * from category";
		
		List<CateGory> list = qr.query(sql, new BeanListHandler<CateGory>(CateGory.class));
		
		return list;
	}

}
