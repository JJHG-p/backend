package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import backend.Model.SesionesModel;

public interface ISesionService {
    SesionesModel crearSesion(SesionesModel sesion);
    List<SesionesModel> listarSesiones();
    SesionesModel buscarSesionPorId(ObjectId id);
}
