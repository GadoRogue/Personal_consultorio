package bo.edu.ucb.software1.demo;

public class PersonalNoEncontrado extends RuntimeException {
    public PersonalNoEncontrado(Long id) {
        super("no se encontro al trabajador" + id);
    }
}
