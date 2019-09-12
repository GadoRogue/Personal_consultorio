package bo.edu.ucb.software1.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
class personal {
    private @Id @GeneratedValue Long id;
    private String name;
    private String puesto;
    private String turno;

    personal(){}

    personal(String name, String puesto, String turno) {
        this.name = name;
        this.puesto = puesto;
        this.turno = turno;
    }
}
