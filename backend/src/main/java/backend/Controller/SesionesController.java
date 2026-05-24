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

import backend.Model.SesionesModel;
import backend.Service.ISesionService;
import jakarta.validation.Valid;

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
}
