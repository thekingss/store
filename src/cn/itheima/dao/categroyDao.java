package cn.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import cn.itheima.bean.CateGory;

public interface categroyDao {

	List<CateGory> findCategroy() throws SQLException;

}
