package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.Model.ComentariosModel;
import backend.Model.ForosModel;
import backend.Model.UsuariosModel;
import backend.Repository.IComentariosRepository;

@Service
public class ComentarioServiceImp implements IComentarioService{
    
    @Autowired
    IComentariosRepository comentariosRepository;

    @Autowired
    IForoService foroService;

    @Autowired
    IUsuarioService usuarioService;

    @Override
    public ComentariosModel crearComentario(ComentariosModel comentario) {

        if (comentariosRepository.existsByForoIdAndUsuarioIdAndContenidoIgnoreCase(comentario.getForoId(), comentario.getUsuarioId(), comentario.getContenido())) {
            throw new IllegalArgumentException("Ya existe un comentario igual publicado por este usuario en el foro.");
        }

        ForosModel foro = foroService.buscarForoPorId(comentario.getForoId());
        if (foro == null) {
            throw new IllegalArgumentException("El foro no existe.");
        }

        if (!"Abierto".equals(foro.getEstado().name())) {
            throw new IllegalArgumentException("Solo se pueden comentar foros en estado Abierto.");
        }

        UsuariosModel usuario = usuarioService.buscarUsuarioPorId(comentario.getUsuarioId());
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario que comenta no está registrado.");
        }

        if (!usuario.isActivo()) {
            throw new IllegalArgumentException("El usuario debe estar activo en el sistema.");
        }

        if (comentario.getReplicasComentarios() != null) {
            for (var replica : comentario.getReplicasComentarios()){
                if (usuarioService.buscarUsuarioPorId(replica.getUsuarioId()) == null) {
                    throw new IllegalArgumentException("El usuario de la réplica no está registrado.");
                }
            }
        }

        return comentariosRepository.save(comentario);
    }

    @Override
    public List<ComentariosModel> listarComentarios() {
        return comentariosRepository.findAll();
    }

    @Override
    public ComentariosModel buscarComentarioPorId(ObjectId id) {
        return comentariosRepository.findById(id).orElse(null);
    }

    @Override
    public ComentariosModel actualizarComentario (ObjectId id, ComentariosModel comentarioActualizado) {
        ComentariosModel comentarioExistente = comentariosRepository.findById(id).orElse(null);

        if (comentarioExistente == null) {
            throw new IllegalArgumentException("El comentario no existe.");
        }

        comentarioActualizado.setId(id);

        return comentariosRepository.save(comentarioActualizado);
    }
}
