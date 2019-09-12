package bo.edu.ucb.software1.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    personal uno(@PathVariable Long id){
        return repositorio.findById(id).orElseThrow(() -> new PersonalNoEncontrado(id));
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
