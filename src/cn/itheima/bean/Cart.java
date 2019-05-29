package cn.itheima.bean;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	private Map<String,CartItem> map = new LinkedHashMap<String,CartItem>();
	
	private double total;

	public Map<String, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}

	public double getTotal() {
		return total;
	}
	
	//清空购物车
	public void clear() {
		map.clear();
		total=0.0;
	}
	
	//删除
	public void remove(String key) {
		CartItem item = map.remove(key);
		total = total-item.getSubtotal();
	}
	
	//添加
	public void add(CartItem item) {
		
		CartItem item2 = map.get(item.getProduct().getPid());
		
		if(item2==null) {
			
			map.put(item.getProduct().getPid(), item);
			
			total = total + item.getSubtotal();
		}else {
			item2.setCount(item2.getCount()+item.getCount());
			total = total + item.getSubtotal();
		}
	}
}
