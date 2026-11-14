package accesoADatos;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import excepciones.*;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 20)
	private String codigo;

	@Column(nullable = false, length = 100)
	private String nombre;

	@Column(length = 120)
	private String email;

	@Column(nullable = false)
	private boolean activo = true;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private TipoCliente tipo;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private RolCliente rol;

	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();

	public Cliente() {
	}

	/**
	 * Para que se guarden bien los dos lados, tanto con pedido como con cliente
	 * 
	 * @param pedido : Pedido
	 */
	public void addPedido(Pedido pedido) {
		// al principio puse que el pedido tuviera ya cliente, pero un pedido nuevo
		// no lo tiene todavia (se lo ponemos aqui), asi que solo compruebo que no sea null
		if (pedido == null) {
			throw new PedidoNoEncontradoException("El pedido no existe");
		}
		pedido.setCliente(this);
		pedidos.add(pedido);
	}

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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public RolCliente getRol() {
		return rol;
	}

	public void setRol(RolCliente rol) {
		this.rol = rol;
	}
	
	
}
