package entities;

public class ProductRule {
	private int id;
	private int productId1;
	private int productId2;
	private int supCount;
	private double conf;
	public ProductRule() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId1() {
		return productId1;
	}
	public void setProductId1(int productId1) {
		this.productId1 = productId1;
	}
	public int getProductId2() {
		return productId2;
	}
	public void setProductId2(int productId2) {
		this.productId2 = productId2;
	}
	public int getSupCount() {
		return supCount;
	}
	public void setSupCount(int supCount) {
		this.supCount = supCount;
	}
	public double getConf() {
		return conf;
	}
	public void setConf(double conf) {
		this.conf = conf;
	}
	
	
}
