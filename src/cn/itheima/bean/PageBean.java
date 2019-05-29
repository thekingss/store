package cn.itheima.bean;

import java.util.List;

public class PageBean<T> 
{
	// 褰撳墠椤�
	private Integer pageNumber;
	// 姣忛〉鏄剧ず鐨勬潯鏁�
	private Integer pageSize;
	// 鎬绘潯鏁�
	private Integer totalCount;
	// 鎬婚〉鏁�
	private Integer totalPage;
	// 姣忛〉鏄剧ず鐨勬暟鎹�
	private List<T> list;
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
	
}
