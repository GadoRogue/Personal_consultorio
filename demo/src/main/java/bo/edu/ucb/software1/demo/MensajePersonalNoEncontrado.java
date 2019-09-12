package bo.edu.ucb.software1.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class MensajePersonalNoEncontrado {
    @ResponseBody
    @ExceptionHandler(PersonalNoEncontrado.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String PersonalNoEncontradoH(PersonalNoEncontrado ex){
        return ex.getMessage();
    }
}
