package isil.proyecto.intapli.domain;

import java.sql.Timestamp;

public class Purchase {
	
	private int idPurchase;
	private double amount;
	private Timestamp purchaseDate;
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
	public Timestamp getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Timestamp purchaseDate) {
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
