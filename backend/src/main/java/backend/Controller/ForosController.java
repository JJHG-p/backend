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

import backend.Model.ForosModel;
import backend.Service.IForoService;
import jakarta.validation.Valid;

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
}
