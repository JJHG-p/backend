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

import backend.Model.SesionesModel;
import backend.Service.ISesionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/UAO/centroComunitario/sesiones")
public class SesionesController {

    @Autowired
    ISesionService sesionService;

    @PostMapping("/")
    public ResponseEntity<SesionesModel> crearSesion(@Valid@RequestBody SesionesModel sesion) {
        return new ResponseEntity<>(sesionService.crearSesion(sesion), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<SesionesModel>> listarSesiones() {
        return new ResponseEntity<>(sesionService.listarSesiones(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarSesion(@PathVariable ObjectId id) {
        SesionesModel sesion = sesionService.buscarSesionPorId(id);

        if (sesion == null) {
            return ResponseEntity.badRequest().body("La sesion no existe.");
        }

        return ResponseEntity.ok(sesion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarSesion(@PathVariable ObjectId id, @RequestBody SesionesModel sesion) {
        try {
            return ResponseEntity.ok(sesionService.actualizarSesion(id, sesion));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
