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

import backend.Model.AnunciosModel;
import backend.Service.IAnuncioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/UAO/centroComunitario/anuncios")
public class AnunciosController {
    
    @Autowired
    IAnuncioService anuncioService;

    @PostMapping("/")
    public ResponseEntity<AnunciosModel> crearAnuncio(@Valid@RequestBody AnunciosModel anuncio) {
        return new ResponseEntity<>(anuncioService.crearAnuncio(anuncio), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<AnunciosModel>> listarAnuncios() {
        return new ResponseEntity<>(anuncioService.listarAnuncios(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarAnuncio(@PathVariable ObjectId id) {
        AnunciosModel anuncio = anuncioService.buscarAnuncioPorId(id);

        if (anuncio == null) {
            return ResponseEntity.badRequest().body("El anuncio no existe.");
        }

        return ResponseEntity.ok(anuncio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAnuncio(@PathVariable ObjectId id, @RequestBody AnunciosModel anuncio) {
        try {
            return ResponseEntity.ok(anuncioService.actualizarAnuncio(id, anuncio));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
