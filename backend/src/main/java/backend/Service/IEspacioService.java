package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import backend.Model.EspaciosModel;

public interface IEspacioService {
    EspaciosModel crearEspacio(EspaciosModel espacio);
    List<EspaciosModel> listarEspacios();
    EspaciosModel buscarEspacioPorId(ObjectId id);
}
