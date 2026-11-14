package presentacion;

import accesoADatos.RolCliente;
import accesoADatos.TipoCliente;

// lo que devuelve la api. No devuelvo la entidad directamente porque con la lista
// de pedidos me salia un bucle infinito en el json (cliente->pedido->cliente...)
public class ClienteResponseDTO {

    private Long id;
    private String codigo;
    private String nombre;
    private String email;
    private boolean activo;
    private TipoCliente tipo;
    private RolCliente rol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

	public RolCliente getRol(RolCliente rol) {
		return rol;
	}

	public void setRol(RolCliente rol) {
		this.rol = rol;
	}
    
    
}
