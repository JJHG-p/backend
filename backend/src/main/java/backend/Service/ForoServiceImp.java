package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.Model.ForosModel;
import backend.Model.UsuariosModel;
import backend.Repository.IForosRepository;

@Service
public class ForoServiceImp implements IForoService{
    
    @Autowired
    IForosRepository forosRepository;

    @Autowired
    IUsuarioService usuarioService;

    @Override
    public ForosModel crearForo(ForosModel foro) {
        UsuariosModel creador = usuarioService.buscarUsuarioPorId(foro.getCreadorId());
        if (creador == null) {
            throw new IllegalArgumentException("El creador del foro no existe.");
        }

        String rolCreador = creador.getRol().name();
        if (!"Coordinador".equals(rolCreador) && !"Instructor".equals(rolCreador)) {
            throw new IllegalArgumentException("Solo Coordinadores e Instructores pueden abrir foros.");
        }

        return forosRepository.save(foro);
    }

    @Override
    public List<ForosModel> listarForos() {
        return forosRepository.findAll();
    }

    @Override
    public ForosModel buscarForoPorId(ObjectId id) {
        return forosRepository.findById(id).orElse(null);
    }
}
