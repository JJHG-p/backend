package backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.Model.ActividadesModel;
import backend.Service.IActividadService;
import jakarta.validation.Valid;

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
}
