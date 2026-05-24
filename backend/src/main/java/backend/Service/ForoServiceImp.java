package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.Model.ForosModel;
import backend.Repository.IForosRepository;

@Service
public class ForoServiceImp implements IForoService{
    
    @Autowired
    IForosRepository forosRepository;

    @Override
    public ForosModel crearForo(ForosModel foro) {
        ForosModel foroRegistrado = forosRepository.save(foro);
        System.out.println(foroRegistrado);
        return foroRegistrado;
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
