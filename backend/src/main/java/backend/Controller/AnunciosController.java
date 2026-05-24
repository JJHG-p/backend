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

import backend.Model.AnunciosModel;
import backend.Service.IAnuncioService;

@RestController
@RequestMapping("/UAO/centroComunitario/anuncios")
public class AnunciosController {
    
    @Autowired
    IAnuncioService anuncioService;

    @PostMapping("/")
    public ResponseEntity<AnunciosModel> crearAnuncio(@RequestBody AnunciosModel anuncio) {
        return new ResponseEntity<>(anuncioService.crearAnuncio(anuncio), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<AnunciosModel>> listarAnuncios() {
        return new ResponseEntity<>(anuncioService.listarAnuncios(), HttpStatus.OK);
    }
}
