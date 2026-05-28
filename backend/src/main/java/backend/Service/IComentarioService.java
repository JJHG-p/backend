package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import backend.Model.ComentariosModel;
import backend.Model.ReplicaComentario;

public interface IComentarioService {
    ComentariosModel crearComentario(ComentariosModel comentario);
    List<ComentariosModel> listarComentarios();
    ComentariosModel buscarComentarioPorId(ObjectId id);
    ComentariosModel actualizarComentario (ObjectId id, ComentariosModel comentarioActualizado);
    ComentariosModel agregarReplica(ObjectId comentarioId, ReplicaComentario replica);
}
