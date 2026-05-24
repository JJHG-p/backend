package backend.Service;

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
        UsuariosModel usuarioRegistrado = usuariosRepository.save(usuario);
        System.out.println(usuarioRegistrado);
        return usuarioRegistrado;
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
