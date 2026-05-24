package backend.Service;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.Model.UsuariosModel;
import backend.Repository.IUsuariosRepository;

@Service
public class UsuarioServiceImp implements IUsuarioService {
    @Autowired 
    IUsuariosRepository usuariosRepository;

    @Override
    public UsuariosModel crearUsuario(UsuariosModel usuario){
        if (usuariosRepository.existsByDocumentoID(usuario.getDocumentoID())) {
            throw new IllegalArgumentException("Ya existe un usuario con el documento: " + usuario.getDocumentoID());
        }

        if (usuariosRepository.existsByEmailIgnoreCase(usuario.getEmail().trim())) {
            throw new IllegalArgumentException("Ya existe un usuario con el email: " + usuario.getEmail());
        }

        if (usuario.getFechaRegistro() == null) {
            usuario.setFechaRegistro(LocalDate.now());
        }

        usuario.setActivo(true);

        return usuariosRepository.save(usuario);
    }

    @Override
    public List<UsuariosModel> listarUsuarios(){
        return usuariosRepository.findAll();
    }

    @Override
    public UsuariosModel buscarUsuarioPorId(ObjectId id){
        return usuariosRepository.findById(id).orElse(null);
    }
}
