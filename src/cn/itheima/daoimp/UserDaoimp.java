package cn.itheima.daoimp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itheima.bean.user;
import cn.itheima.dao.UserDao;
import cn.itheima.utils.DataSourceUtils;

public class UserDaoimp implements UserDao {
	public void save(user user) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into users values(?,?,?,?,?,?,?,?,?)";
		Object[] obj={user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getBirthday(),user.getSex(),user.getState(),user.getCode()};
		// hibernate¿ò¼Ü    qr.save(user);
		qr.update(sql,obj);
	}
	
	public user FindBuCode(String code) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from users where code=?";
		user user = qr.query(sql, new BeanHandler<user>(user.class), code);
		return user;
	}
	
	public void update(user user) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update users set state=? where code=?";
		qr.update(sql, user.getState(),user.getCode());
	}
	
	public user login(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from users where username=? and password=?";
		user user = qr.query(sql, new BeanHandler<user>(user.class), username,password);
		return user;
	}
}
