package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import backend.Model.UsuariosModel;

public interface IUsuarioService {
    public UsuariosModel crearUsuario(UsuariosModel usuario);
    public List<UsuariosModel> listarUsuarios();
    public UsuariosModel buscarUsuarioPorId(ObjectId idUsuario);
}
