package excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// antes hacia try/catch en el controlador, pero con esto Spring devuelve 404 el solo
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNoEncontradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	public ClienteNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
