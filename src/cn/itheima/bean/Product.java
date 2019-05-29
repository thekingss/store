package cn.itheima.bean;

public class Product 
{
	private String pid; 
	private String pname; 
	private double market_price; 
	private double shop_price;
	private String pimage; 
	private String pdate; 
	private Integer is_hot; 
	private String pdesc; 
	private Integer pflag; 
	
	// 澶栭敭 --鍙鏄閿�  灏变竴瀹氳鐢ㄥ閿寚鍚戠殑瀵硅薄鏉ヨ〃绀�
	private CateGory category; 
	

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getMarket_price() {
		return market_price;
	}

	public void setMarket_price(double market_price) {
		this.market_price = market_price;
	}

	public double getShop_price() {
		return shop_price;
	}

	public void setShop_price(double shop_price) {
		this.shop_price = shop_price;
	}

	public String getPimage() {
		return pimage;
	}

	public void setPimage(String pimage) {
		this.pimage = pimage;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public Integer getIs_hot() {
		return is_hot;
	}

	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public Integer getPflag() {
		return pflag;
	}

	public void setPflag(Integer pflag) {
		this.pflag = pflag;
	}

	public CateGory getCategory() {
		return category;
	}

	public void setCategory(CateGory category) {
		this.category = category;
	}

	
    
    
    
}
