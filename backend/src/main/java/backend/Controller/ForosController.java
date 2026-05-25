package backend.Controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.Model.ForosModel;
import backend.Service.IForoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/UAO/centroComunitario/foros")
public class ForosController {
    
    @Autowired
    IForoService foroService;

    @PostMapping("/")
    public ResponseEntity<ForosModel> crearForo(@Valid@RequestBody ForosModel foro) {
        return new ResponseEntity<>(foroService.crearForo(foro), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ForosModel>> listarForos() {
        return new ResponseEntity<>(foroService.listarForos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarForo(@PathVariable ObjectId id) {
        ForosModel foro = foroService.buscarForoPorId(id);

        if (foro == null) {
            return ResponseEntity.badRequest().body("El foro no existe.");
        }

        return ResponseEntity.ok(foro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarForo(@PathVariable ObjectId id, @RequestBody ForosModel foro) {
        try {
            return ResponseEntity.ok(foroService.actualizarForo(id, foro));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
