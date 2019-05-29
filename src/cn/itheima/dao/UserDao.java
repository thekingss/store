package cn.itheima.dao;

import java.sql.SQLException;

import cn.itheima.bean.user;

public interface UserDao {

	void save(user user) throws SQLException;

	user FindBuCode(String code) throws SQLException;

	void update(user user) throws SQLException;

	user login(String username, String password) throws SQLException;

}
