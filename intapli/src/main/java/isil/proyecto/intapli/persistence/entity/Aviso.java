package isil.proyecto.intapli.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="aviso")
public class Aviso {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idaviso")
    private Integer idAviso;
	
	private String contenido;
	
	@Column(name = "idusuario")
    private Integer idUsuario;


    @ManyToOne
    @JoinColumn(name = "idusuario", insertable = false, updatable = false)
    private Usuario usuario;


	public Integer getIdAviso() {
		return idAviso;
	}


	public void setIdAviso(Integer idAviso) {
		this.idAviso = idAviso;
	}


	public String getContenido() {
		return contenido;
	}


	public void setContenido(String contenido) {
		this.contenido = contenido;
	}


	public Integer getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
