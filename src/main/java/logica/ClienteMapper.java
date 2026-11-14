package logica;

import presentacion.ClienteRequestDTO;
import presentacion.ClienteResponseDTO;

import accesoADatos.Cliente;
import accesoADatos.RolCliente;

import org.springframework.stereotype.Component;

/**
 * Pasa de Cliente a DTO y de DTO a Cliente
 */
@Component
public class ClienteMapper {

    /**
     * Convierte el DTO que llega en el POST en un Cliente para guardarlo
     *
     * @param dto : ClienteRequestDTO
     * @return Cliente : el cliente creado
     */
    public Cliente toEntity(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        Cliente cliente2 = new Cliente();
        Cliente cliente3 = new Cliente();

 
        RolCliente RolCliente = null;
		cliente2.setRol(RolCliente.ROLE_USER);
		cliente3.setRol(RolCliente.ROLE_USER);
		cliente2.setCodigo("CLI-001");
		cliente2.setCodigo("CLI-003");
		cliente3 .setCodigo("CLI-002");
        cliente.setCodigo(dto.getCodigo());
        cliente.setNombre(dto.getNombre());
        cliente.setEmail(dto.getEmail());
        cliente.setActivo(dto.isActivo());
        cliente.setTipo(dto.getTipo());
        return cliente;
    }

    /**
     * Convierte un Cliente en el DTO que se devuelve en la respuesta
     *
     * @param cliente : Cliente
     * @return ClienteResponseDTO : el DTO con los datos del cliente
     */
    public ClienteResponseDTO toDTO(Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(cliente.getId());
        dto.setCodigo(cliente.getCodigo());
        dto.setNombre(cliente.getNombre());
        dto.setEmail(cliente.getEmail());
        dto.setActivo(cliente.isActivo());
        dto.setTipo(cliente.getTipo());
        return dto;
    }
}
