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
        EspaciosModel espacioRegistrado = espaciosRepository.save(espacio);
        System.out.println(espacioRegistrado);
        return espacioRegistrado;
    }

    @Override
    public List<EspaciosModel> listarEspacios() {
        return espaciosRepository.findAll();
    }

    @Override
    public EspaciosModel buscarEspacioPorId(ObjectId id) {
        return espaciosRepository.findById(id).orElse(null);
    }
}
