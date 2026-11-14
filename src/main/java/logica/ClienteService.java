package logica;

import accesoADatos.*;
import presentacion.*;
import excepciones.ClienteNoEncontradoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase con la logica de los clientes y sus pedidos
 */
@Service
@Transactional
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteMapper clienteMapper;

	@Autowired
	private PedidoMapper pedidoMapper;

	/**
	 * Devuelve todos los clientes
	 *
	 * @return List : lista de clientes
	 */
	public List<ClienteResponseDTO> listarTodos() {
		List<ClienteResponseDTO> lista = new ArrayList<>();
		for (Cliente cliente : clienteRepository.findAll()) {
			lista.add(clienteMapper.toDTO(cliente));
		}
		return lista;
	}
	/**
	 * Devuelve un cliente
	 *
	 * @return List : lista de clientes
	 */
	public List<ClienteResponseDTO> listarUnCliente(Cliente clienteParametro) {
		for (Cliente cliente : clienteRepository.findAll()) {
			if(cliente == clienteParametro) {
				
			}
		}
		return lista;
	}

	/**
	 * Devuelve solo los clientes activos ordenados por codigo
	 *
	 * @return List : lista de clientes activos
	 */
	public List<ClienteResponseDTO> listarActivos() {
		// primero lo hice con findAll y un if, pero el enunciado pide metodo derivado
		List<ClienteResponseDTO> lista = new ArrayList<>();
		for (Cliente cliente : clienteRepository.findByActivoTrueOrderByCodigoAsc()) {
			lista.add(clienteMapper.toDTO(cliente));
		}
		return lista;
	}

	/**
	 * Busca un cliente por su codigo, si no existe lanza una excepcion
	 *
	 * @param codigo : String
	 * @return ClienteResponseDTO : el cliente encontrado
	 */
	public ClienteResponseDTO buscarPorCodigo(String codigo) {
		Cliente cliente = buscarCliente(codigo);
		if (cliente == null) {
			throw new ClienteNoEncontradoException("No existe el cliente " + codigo);
		}
		return clienteMapper.toDTO(cliente);
	}

	/**
	 * Busca el cliente recorriendo todos, si no esta devuelve null
	 *
	 * @param codigo : String
	 * @return Cliente : el cliente o null
	 */
	private Cliente buscarCliente(String codigo) {
		for (Cliente cliente : clienteRepository.findAll()) {
			if (cliente.getCodigo().equals(codigo)) {
				return cliente;
			}
		}
		return null;
	}

	/**
	 * Devuelve solo los clientes que son de tipo EMPRESA
	 *
	 * @return List : lista de clientes empresa
	 */
	public List<ClienteResponseDTO> listarEmpresas() {
		List<ClienteResponseDTO> lista = new ArrayList<>();
		for (Cliente cliente : clienteRepository.findAll()) {
			if (cliente.getTipo() == TipoCliente.EMPRESA) {
				lista.add(clienteMapper.toDTO(cliente));
			}
		}
		return lista;
	}

	/**
	 * Guarda un cliente nuevo en la base de datos
	 *
	 * @param dto : ClienteRequestDTO
	 * @return ClienteResponseDTO : el cliente guardado
	 */
	public ClienteResponseDTO crear(ClienteRequestDTO dto) {
		Cliente cliente = clienteRepository.save(clienteMapper.toEntity(dto));
		return clienteMapper.toDTO(cliente);
	}

	/**
	 * Devuelve los pedidos de un cliente a partir de su id
	 *
	 * @param id : Long
	 * @return List : lista de pedidos del cliente
	 */
	public List<PedidoDTO> pedidosDeCliente(Long id) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ClienteNoEncontradoException("No existe el cliente con id " + id));
		List<PedidoDTO> lista = new ArrayList<>();
		// aqui me daba LazyInitializationException, lo arregle poniendo @Transactional
		// en la clase
		for (Pedido pedido : cliente.getPedidos()) {
			lista.add(pedidoMapper.toDTO(pedido));
		}
		return lista;
	}

	/**
	 * Crea un pedido nuevo y lo asocia al cliente con ese id
	 *
	 * @param id  : Long
	 * @param dto : PedidoDTO
	 * @return PedidoDTO : el pedido creado
	 */
	public PedidoDTO crearPedido(Long id, PedidoDTO dto) {
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new ClienteNoEncontradoException("No existe el cliente con id " + id));
		Pedido pedido = pedidoMapper.toEntity(dto);
		// antes solo hacia p.setCliente(c) y en la lista del cliente no salia,
		// hay que poner los dos lados de la relacion
		cliente.addPedido(pedido);
		pedidoRepository.save(pedido);
		return pedidoMapper.toDTO(pedido);
	}

	/**
	 * Si el cliente no existe lo crea y luego le mete el pedido, todo en la misma
	 * transaccion
	 *
	 * @param dto : ClienteConPedidoRequestDTO
	 * @return PedidoDTO : el pedido creado
	 */
	public PedidoDTO crearClienteConPedido(ClienteConPedidoRequestDTO dto) {
		Cliente cliente = buscarCliente(dto.getCliente().getCodigo());
		if (cliente == null) {
			cliente = clienteRepository.save(clienteMapper.toEntity(dto.getCliente()));
		}
		Pedido producto = pedidoMapper.toEntity(dto.getPedido());
		cliente.addPedido(producto);
		pedidoRepository.save(producto);
		return pedidoMapper.toDTO(producto);
	}
}
