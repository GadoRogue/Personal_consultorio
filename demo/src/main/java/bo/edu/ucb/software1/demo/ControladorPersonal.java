package bo.edu.ucb.software1.demo;

import org.hibernate.annotations.common.reflection.XMethod;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class ControladorPersonal {
    private Repositorio_personal repositorio;


    public ControladorPersonal(Repositorio_personal repositorio) {
        this.repositorio = repositorio;
    }
    //agregar ruta
    @GetMapping("/personal")
    List<personal> all(){
        return repositorio.findAll();
    }
    @PostMapping("/personal")
    personal nuevoper(@RequestBody personal nuevoper){
        return repositorio.save(nuevoper);
    }
    //unico objeto
    @GetMapping("/personal/{id}")
    personal one(@PathVariable Long id){
        personal persona = repositorio.findById(id).orElseThrow(() -> new PersonalNoEncontrado(id));
        return new Resource<>(persona,
                linkTo(methodOn(ControladorPersonal.class).one(id)).withSelfRel(),
                linkTo(methodOn(ControladorPersonal.class).all()).withRel("personal"));
    }
    @PutMapping("/personal{id}")
    personal reemplazarPersonal (@RequestBody personal nuevoper, @PathVariable Long id){
        return repositorio.findById(id).map(personal -> {personal.setName(nuevoper.getName());
                                                        personal.setPuesto(nuevoper.getPuesto());
                                                        personal.setTurno(nuevoper.getTurno());
                                                        return repositorio.save(personal); }).orElseGet(() -> {
                                                        nuevoper.setId(id);
                                                        return repositorio.save(nuevoper);
        });

    }
    @DeleteMapping("/personal/{id}")
    void BorrarPersonal(@PathVariable Long id){
        repositorio.deleteById(id);
    }
}
