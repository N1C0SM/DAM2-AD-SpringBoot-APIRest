package controlador;

import accesoADatos.Cliente;
import presentacion.*;
import logica.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import accesoADatos.RolCliente;

import java.util.List;

/**
 * Controlador de la API, recibe las peticiones y llama al service
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	/**
	 * GET /api/clientes, devuelve a los clientes solo lo que tienen permitido ver
	 *
	 * @return List : lista de clientes
	 */
	@GetMapping
	public List<ClienteResponseDTO> listar(Cliente clienteParametro) {
		for (ClienteResponseDTO  cliente: clienteService.listarTodos()) {
			if(cliente.getNombre().equals(clienteParametro.getNombre())) {
				clienteService.listarUnCliente(clienteParametro);
				
			}
		}
		return clienteService.listarTodos();
	}

	/**
	 * GET /api/clientes/activos, devuelve los clientes activos
	 *
	 * @return List : lista de clientes activos
	 */
	@GetMapping("/activos")
	public List<ClienteResponseDTO> activos() {
		return clienteService.listarActivos();
	}

	/**
	 * GET /api/clientes/codigo/{codigo}, busca un cliente por su codigo
	 *
	 * @param codigo : String
	 * @return ClienteResponseDTO : el cliente (404 si no existe)
	 */
	@GetMapping("/codigo/{codigo}")
	public ClienteResponseDTO porCodigo(@PathVariable String codigo) {
		return clienteService.buscarPorCodigo(codigo);
	}

	/**
	 * GET /api/clientes/empresa, devuelve los clientes de tipo EMPRESA
	 *
	 * @return List : lista de clientes empresa
	 */
	@GetMapping("/empresa")
	public List<ClienteResponseDTO> empresas() {
		return clienteService.listarEmpresas();
	}

	/**
	 * POST /api/clientes, crea un cliente nuevo (devuelve 201)
	 *
	 * @param dto : ClienteRequestDTO
	 * @return ClienteResponseDTO : el cliente creado
	 */
	// al principio los POST devolvian 200, con esto devuelven 201 (creado)
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteResponseDTO crear(@RequestBody ClienteRequestDTO dto) {
		return clienteService.crear(dto);
	}

	/**
	 * GET /api/clientes/{id}/pedidos, devuelve los pedidos de un cliente
	 *
	 * @param id : Long
	 * @return List : lista de pedidos (404 si no existe el cliente)
	 */
	@GetMapping("/{id}/pedidos")
	public List<PedidoDTO> pedidos(@PathVariable Long id) {
		return clienteService.pedidosDeCliente(id);
	}

	/**
	 * POST /api/clientes/{id}/pedidos, crea un pedido y lo asocia al cliente
	 *
	 * @param id  : Long
	 * @param dto : PedidoDTO
	 * @return PedidoDTO : el pedido creado (404 si no existe el cliente)
	 */
	@PostMapping("/{id}/pedidos")
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoDTO crearPedido(@PathVariable Long id, @RequestBody PedidoDTO dto) {
		return clienteService.crearPedido(id, dto);
	}

	/**
	 * POST /api/clientes/con-pedido, crea el cliente si no existe y le asocia el
	 * pedido
	 *
	 * @param dto : ClienteConPedidoRequestDTO
	 * @return PedidoDTO : el pedido creado
	 */
	@PostMapping("/con-pedido")
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoDTO conPedido(@RequestBody ClienteConPedidoRequestDTO dto) {
		return clienteService.crearClienteConPedido(dto);
	}

	/**
	 * POST /api/pedido/con-cliente-autenticado, crea el pedido si el cliente esta
	 * autenticado, sino esta autenticado, no lo crea
	 *
	 * @param dto : ClienteConPedidoRequestDTO
	 * @return PedidoDTO : el pedido creado
	 */
	@PostMapping("/api/pedidos/cliente{id}")
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public PedidoDTO ClienteConPedido(@RequestBody ClienteConPedidoRequestDTO dto) {
		for (ClienteResponseDTO  cliente: clienteService.listarTodos()) {
			if(cliente.getRol(RolCliente.ROLE_ADMIN) == null) {
				System.out.println("Usuario no autorizado");
				
			}
		}
		return clienteService.crearClienteConPedido(dto);
	}
	/**
	 * POST /api/admin, solo permite acceder si el usuario tiene rol admin
	 */
	@PostMapping("/api/admin/cliente{id}")
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public void ClienteNoAutorizado(ClienteRequestDTO cliente) {
		if(cliente.getRol(RolCliente.ROLE_ADMIN) == null) {
			System.out.println("Cliente no autorizado");
		}
	
	}
}
