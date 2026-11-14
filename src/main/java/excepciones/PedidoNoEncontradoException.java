package excepciones;

public class PedidoNoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PedidoNoEncontradoException(String mensaje) {
		super(mensaje);
	}

}
