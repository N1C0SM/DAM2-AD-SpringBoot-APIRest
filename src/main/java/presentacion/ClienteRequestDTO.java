package presentacion;

import accesoADatos.RolCliente;
import accesoADatos.TipoCliente;

// lo que llega en el POST para crear un cliente
public class ClienteRequestDTO {

    private String codigo;
    private String nombre;
    private String email;
    private boolean activo = true;
    private TipoCliente tipo;
    private RolCliente rol;

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
    public RolCliente getRol(RolCliente roleAdmin) {
        return rol;
    }

    public void setRol(RolCliente rol) {
        this.rol = rol;
    }
}
