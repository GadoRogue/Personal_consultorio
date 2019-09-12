package bo.edu.ucb.software1.demo;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repositorio_personal extends JpaRepository<personal, Long> {

}
