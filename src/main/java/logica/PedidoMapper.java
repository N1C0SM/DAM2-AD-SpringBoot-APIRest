package logica;

import presentacion.PedidoDTO;

import accesoADatos.Pedido;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Pasa de Pedido a DTO y de DTO a Pedido
 */
@Component
public class PedidoMapper {

    /**
     * Convierte el DTO que llega en el POST en un Pedido, si no trae fecha pone la
     * de hoy
     *
     * @param dto : PedidoDTO
     * @return Pedido : el pedido creado
     */
    public Pedido toEntity(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        // si no viene fecha pongo la de hoy
        if (dto.getFecha() == null) {
        	pedido.setFecha(LocalDate.now());
        } else {
        	pedido.setFecha(dto.getFecha());
        }
        pedido.setDescripcion(dto.getDescripcion());
        pedido.setImporte(dto.getImporte());
        return pedido;
    }

    /**
     * Convierte un Pedido en el DTO que se devuelve en la respuesta
     *
     * @param pedido : Pedido
     * @return PedidoDTO : el DTO con los datos del pedido
     */
    public PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setFecha(pedido.getFecha());
        dto.setDescripcion(pedido.getDescripcion());
        dto.setImporte(pedido.getImporte());
        dto.setClienteId(pedido.getCliente().getId());
        return dto;
    }
}
