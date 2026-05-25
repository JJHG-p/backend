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

import backend.Model.ActividadesModel;
import backend.Service.IActividadService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/UAO/centroComunitario/actividades")
public class ActividadesController {
    
    @Autowired
    IActividadService actividadService;

    @PostMapping("/")
    public ResponseEntity<ActividadesModel> crearActividad(@Valid@RequestBody ActividadesModel actividad) {
        return new ResponseEntity<>(actividadService.crearActividad(actividad), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ActividadesModel>> listarActividades() {
        return new ResponseEntity<>(actividadService.listarActividades(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarActividad(@PathVariable ObjectId id) {
        
        ActividadesModel actividad = actividadService.buscarActividadPorId(id);

        if (actividad == null) {
            return ResponseEntity.badRequest().body("La actividad no existe.");
        }

        return ResponseEntity.ok(actividad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarActividad(@PathVariable ObjectId id, @RequestBody ActividadesModel actividad) {
        try {
            return ResponseEntity.ok(
                actividadService.actualizarActividad(id, actividad)
            );  
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
