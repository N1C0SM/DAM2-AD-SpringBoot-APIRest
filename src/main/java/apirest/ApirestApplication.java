package apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// al separar los paquetes Spring no encontraba el controlador ni los repositorios
// (solo busca dentro del paquete apirest), asi que le digo donde estan
@SpringBootApplication(scanBasePackages = {"controlador", "logica", "accesoADatos", "excepciones", "presentacion"})
@EnableJpaRepositories("accesoADatos")
@EntityScan("accesoADatos")
public class ApirestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApirestApplication.class, args);
    }
}
