package bo.edu.ucb.software1.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j

public class CargarBD {

    @Bean
    CommandLineRunner initDataBase (Repositorio_personal repositorio){
        return args ->{
<<<<<<< HEAD
          log.info("Precargado"+ repositorio.save(new personal("Juan Perez","Pasante","manana")));
          log.info("Precargado"+ repositorio.save(new personal("Maria Flores", "Secretaria", "tarde")));
            log.info("Precargado"+ repositorio.save(new personal("Martina Esposito","Pasante","noche")));
            log.info("Precargado"+ repositorio.save(new personal("Rodrigo Pomar", "Pasante", "noche")));
=======
          log.info("Precargado"+ repositorio.save(new Personal("Juan Perez","Pasante","manana")));
          log.info("Precargado"+ repositorio.save(new Personal("Maria Flores", "Secretaria", "tarde")));
>>>>>>> c239b3f1131dfc0c186efa28b173d18cdc337d96
        };
    }
}
