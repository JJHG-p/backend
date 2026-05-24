package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import backend.Model.ForosModel;

public interface IForoService {
    ForosModel crearForo(ForosModel foro);
    List<ForosModel> listarForos();
    ForosModel buscarForoPorId(ObjectId id);
}
