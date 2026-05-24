package backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import backend.Model.UsuariosModel;
import backend.Service.IUsuarioService;

@RestController
@RequestMapping("/UAO/centroComunitario/usuarios")
public class UsuariosController {
    
    @Autowired
    IUsuarioService usuarioService;

    @PostMapping("/")
    public ResponseEntity<UsuariosModel> crearUsuario(@RequestBody UsuariosModel usuario) {        
        return new ResponseEntity<>(usuarioService.crearUsuario(usuario), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UsuariosModel>> listarUsuarios() {
        return new ResponseEntity<>(usuarioService.listarUsuarios(), HttpStatus.OK);
    }
    
}
