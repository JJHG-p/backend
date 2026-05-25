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

import backend.Model.EspaciosModel;
import backend.Service.IEspacioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/UAO/centroComunitario/espacios")
public class EspaciosController {
    
    @Autowired
    IEspacioService espacioService;

    @PostMapping("/")
    public ResponseEntity<EspaciosModel> crearEspacio(@Valid@RequestBody EspaciosModel espacio) {
        return new ResponseEntity<>(espacioService.crearEspacio(espacio), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<EspaciosModel>> listarEspacios() {
        return new ResponseEntity<>(espacioService.listarEspacios(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEspacio(@PathVariable ObjectId id) {
        EspaciosModel espacio = espacioService.buscarEspacioPorId(id);

        if (espacio == null) {
            return ResponseEntity.badRequest().body("El espacio no existe");
        }

        return ResponseEntity.ok(espacio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEspacio(@PathVariable ObjectId id, @RequestBody EspaciosModel espacio) {
        try {
            return ResponseEntity.ok(espacioService.actualizarEspacio(id, espacio));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
