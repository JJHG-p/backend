package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.Model.EspaciosModel;
import backend.Repository.IEspaciosRepository;

@Service
public class EspacioServiceImp implements IEspacioService{
    
    @Autowired
    IEspaciosRepository espaciosRepository;

    @Override
    public EspaciosModel crearEspacio(EspaciosModel espacio) {
        if (espaciosRepository.existsByNombreIgnoreCaseAndUbicacionIgnoreCase(espacio.getNombre(), espacio.getUbicacion())) {
            throw new IllegalArgumentException("Ya existe un espacio con ese nombre y ubicación.");
        }
        return espaciosRepository.save(espacio);
    }

    @Override
    public List<EspaciosModel> listarEspacios() {
        return espaciosRepository.findAll();
    }

    @Override
    public EspaciosModel buscarEspacioPorId(ObjectId id) {
        return espaciosRepository.findById(id).orElse(null);
    }

    @Override
    public EspaciosModel actualizarEspacio (ObjectId id, EspaciosModel espacioActualizado) {
        EspaciosModel espacioExistente = espaciosRepository.findById(id).orElse(null);

        if (espacioExistente == null) {
            throw new IllegalArgumentException("El espacio no existe.");
        }

        espacioActualizado.setId(id);

        return espaciosRepository.save(espacioActualizado);
    }
}
