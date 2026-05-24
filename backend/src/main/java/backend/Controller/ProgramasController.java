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

import backend.Model.ProgramasModel;
import backend.Service.IProgramaService;

@RestController
@RequestMapping("/UAO/centroComunitario/programas")
public class ProgramasController {
    
    @Autowired
    IProgramaService programaService;

    @PostMapping("/")
    public ResponseEntity<ProgramasModel> crearPrograma(@RequestBody ProgramasModel programa) {
        return new ResponseEntity<>(programaService.crearPrograma(programa), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProgramasModel>> listarProgramas() {
        return new ResponseEntity<>(programaService.listarProgramas(), HttpStatus.OK);
    }
}
