package backend.Controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.Model.ComentariosModel;
import backend.Model.ReplicaComentario;
import backend.Service.IComentarioService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/UAO/centroComunitario/comentarios")
public class ComentariosController {
    
    @Autowired
    IComentarioService comentarioService;

    @PostMapping("/")
    public ResponseEntity<ComentariosModel> crearComentario(@Valid@RequestBody ComentariosModel comentario) {
        return new ResponseEntity<>(comentarioService.crearComentario(comentario), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ComentariosModel>> listarComentarios() {
        return new ResponseEntity<>(comentarioService.listarComentarios(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarComentario(@PathVariable ObjectId id) {
        ComentariosModel comentario = comentarioService.buscarComentarioPorId(id);

        if (comentario == null) {
            return ResponseEntity.badRequest().body("El comentario no existe.");
        }

        return ResponseEntity.ok(comentario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarComentario(@PathVariable ObjectId id, @RequestBody ComentariosModel comentario) {
        try {
            return ResponseEntity.ok(comentarioService.actualizarComentario(id, comentario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/replicas")
    public ResponseEntity<?> agregarReplica(@PathVariable ObjectId id, @Valid @RequestBody ReplicaComentario replica) {
        try {
            return new ResponseEntity<>(comentarioService.agregarReplica(id, replica), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    

}
