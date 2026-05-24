package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import backend.Model.ActividadesModel;


public interface IActividadService {
    ActividadesModel crearActividad(ActividadesModel actividad);
    List<ActividadesModel> listarActividades();
    ActividadesModel buscarActividadPorId(ObjectId id);
    ActividadesModel obtenerActividadPorId(ObjectId id);
}
