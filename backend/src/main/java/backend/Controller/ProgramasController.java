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

import backend.Model.ProgramasModel;
import backend.Service.IProgramaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/UAO/centroComunitario/programas")
public class ProgramasController {
    
    @Autowired
    IProgramaService programaService;

    @PostMapping("/")
    public ResponseEntity<ProgramasModel> crearPrograma(@Valid@RequestBody ProgramasModel programa) {
        return new ResponseEntity<>(programaService.crearPrograma(programa), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProgramasModel>> listarProgramas() {
        return new ResponseEntity<>(programaService.listarProgramas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPrograma(@PathVariable ObjectId id) {
        ProgramasModel programa = programaService.buscarProgramaPorId(id);

        if (programa == null) {
            return ResponseEntity.badRequest().body("El programa no existe.");
        }

        return ResponseEntity.ok(programa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPrograma(@PathVariable ObjectId id, @RequestBody ProgramasModel programa) {
        try {
            return ResponseEntity.ok(programaService.actualizarPrograma(id, programa));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
