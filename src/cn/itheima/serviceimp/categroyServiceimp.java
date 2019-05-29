package cn.itheima.serviceimp;

import java.sql.SQLException;
import java.util.List;

import cn.itheima.bean.CateGory;
import cn.itheima.dao.categroyDao;
import cn.itheima.daoimp.categroyDaoimp;
import cn.itheima.service.categroyService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class categroyServiceimp implements categroyService {

	public String findCategroy() throws SQLException {
		categroyDao cd = new categroyDaoimp();
		
		List<CateGory> list = cd.findCategroy();
		
		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
	}

}
