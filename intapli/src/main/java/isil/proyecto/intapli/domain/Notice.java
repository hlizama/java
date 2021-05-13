package isil.proyecto.intapli.domain;

public class Notice {

    private int idmaviso;
    private String contenidoaviso;
    private int idusuario;
	private User user;

    public int getIdmaviso() {
        return idmaviso;
    }

    public void setIdmaviso(int idmaviso) {
        this.idmaviso = idmaviso;
    }

    public String getContenidoaviso() {
        return contenidoaviso;
    }

    public void setContenidoaviso(String contenidoaviso) {
        this.contenidoaviso = contenidoaviso;
    }

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    

}
