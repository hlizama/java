package pe.isil.model;

//import java.sql.Timestamp;

public class Purchase {
	
	private int idPurchase;
	private double amount;
	private String purchaseDate;
	private int productId;
	private int idusuario;
	
	public int getIdPurchase() {
		return idPurchase;
	}
	public void setIdPurchase(int idPurchase) {
		this.idPurchase = idPurchase;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	

}
