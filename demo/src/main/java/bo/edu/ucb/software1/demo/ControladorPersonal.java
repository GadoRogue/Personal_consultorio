package bo.edu.ucb.software1.demo;

import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    Resources<org.springframework.hateoas.Resource<Personal>> all(){
        List<Resource<Personal>> personas = repositorio.findAll().stream().map(personal -> new Resource<>(personal,
                                    linkTo(methodOn(ControladorPersonal.class).one(personal.getId())).withSelfRel(),
                                    linkTo(methodOn(ControladorPersonal.class).all()).withSelfRel("personal"))).collect(Collectors.toList());

        return new Resources<>(personas, linkTo(methodOn(ControladorPersonal.class).all()).withSelfRel());
    }

    //unico objeto
    @GetMapping("/personal/{id}")
    org.springframework.hateoas.Resource<Personal> one(@PathVariable Long id){
        Personal persona = repositorio.findById(id).orElseThrow(() -> new PersonalNoEncontrado(id));
        return new Resources<>(persona,
                linkTo(methodOn(ControladorPersonal.class).one(id)).withSelfRel(),
                linkTo(methodOn(ControladorPersonal.class).all()).withRel("personal"));
    }
    @PutMapping("/personal{id}")
    Personal reemplazarPersonal (@RequestBody Personal nuevoper, @PathVariable Long id){
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
