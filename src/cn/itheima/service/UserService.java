package cn.itheima.service;

import java.sql.SQLException;

import cn.itheima.bean.user;

public interface UserService {

	void save(user user) throws SQLException;

	user FindByCode(String code) throws SQLException;

	void update(user user) throws SQLException;

	user login(String username, String password) throws SQLException;

}
