package backend.Controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import backend.Model.UsuariosModel;
import backend.Service.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/UAO/centroComunitario/usuarios")
public class UsuariosController {
    
    @Autowired
    IUsuarioService usuarioService;

    @PostMapping("/")
    public ResponseEntity<UsuariosModel> crearUsuario(@Valid@RequestBody UsuariosModel usuario) {        
        return new ResponseEntity<>(usuarioService.crearUsuario(usuario), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UsuariosModel>> listarUsuarios() {
        return new ResponseEntity<>(usuarioService.listarUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuario(@PathVariable ObjectId id) {
        UsuariosModel usuario = usuarioService.buscarUsuarioPorId(id);

        if (usuario == null) {
            return ResponseEntity.badRequest().body("El usuario no existe.");
        }

        return ResponseEntity.ok(usuario);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable ObjectId id, @RequestBody UsuariosModel usuario) {
        try {
            return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
