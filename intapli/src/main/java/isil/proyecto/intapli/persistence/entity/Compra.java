package isil.proyecto.intapli.persistence.entity;

import lombok.Data;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="compra")

public class Compra {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcompra")
    private Integer idCompra;
	
	private double monto;
	private Timestamp fechacompra;
	
	@Column(name = "idproducto")
	private int idProducto;
	
	@Column(name = "idusuario")
	private int idUsuario;
	
	 @ManyToOne
	 @JoinColumn(name = "idusuario", insertable = false, updatable = false)
	 private Usuario usuario;
	 
	 @ManyToOne
	 @JoinColumn(name = "idproducto", insertable = false, updatable = false)
	 private Producto producto;
	 

	public Integer getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Timestamp getFechacompra() {
		return fechacompra;
	}

	public void setFechacompra(Timestamp fechacompra) {
		this.fechacompra = fechacompra;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	 
	 
	 
	 


}
