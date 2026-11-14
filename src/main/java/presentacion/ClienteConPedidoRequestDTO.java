package presentacion;

// para el POST /con-pedido, viene el cliente y el pedido juntos
public class ClienteConPedidoRequestDTO {

    private ClienteRequestDTO cliente;
    private PedidoDTO pedido;

    public ClienteRequestDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteRequestDTO cliente) {
        this.cliente = cliente;
    }

    public PedidoDTO getPedido() {
        return pedido;
    }

    public void setPedido(PedidoDTO pedido) {
        this.pedido = pedido;
    }
}
