package cn.itheima.serviceimp;

import java.sql.SQLException;

import cn.itheima.bean.user;
import cn.itheima.dao.UserDao;
import cn.itheima.daoimp.UserDaoimp;
import cn.itheima.service.UserService;

public class UserServiceimp implements UserService {
	public void save(user user) throws SQLException {
		UserDao ud = new UserDaoimp();
		ud.save(user);
	}
	public user FindByCode(String code) throws SQLException {
		UserDao ud = new UserDaoimp();
		return ud.FindBuCode(code);
	}
	
	public void update(user user) throws SQLException {
		UserDao ud = new UserDaoimp();
		ud.update(user);
	}
	
	public user login(String username, String password) throws SQLException {
		UserDao ud = new UserDaoimp();
		return ud.login(username,password);
	}
}
