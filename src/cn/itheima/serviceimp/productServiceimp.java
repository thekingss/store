package cn.itheima.serviceimp;

import java.sql.SQLException;
import java.util.List;

import cn.itheima.bean.PageBean;
import cn.itheima.bean.Product;
import cn.itheima.dao.productDao;
import cn.itheima.daoimp.productDaoimp;
import cn.itheima.service.productService;

public class productServiceimp implements productService {

	public PageBean findProduct(String cid, int pageNumber, int pageSize) throws SQLException {
		
		productDao pd = new productDaoimp();
		
		int totalCount = pd.findCount(cid);
		int totalPage=0;
		if(totalCount%pageSize==0) {
			totalPage=totalCount/pageSize;
		}
		if(totalCount%pageSize!=0) {
			totalPage=totalCount/pageSize+1;
		}
		
		List<Product> list = pd.findList(cid,(pageNumber-1)*pageSize,pageSize);
		PageBean pb = new PageBean();
		pb.setPageNumber(pageNumber);
		pb.setPageSize(pageSize);
		pb.setTotalCount(totalCount);
		pb.setTotalPage(totalPage);
		pb.setList(list);
		return pb;
	}

	public Product findByPid(String pid) throws SQLException {
		productDao pd = new productDaoimp();
		
		return pd.findByPid(pid);
	}
	
	public List<Product> hotList() throws SQLException{
		productDao pd = new productDaoimp();
		return pd.hotList();
	}

}
