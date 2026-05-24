package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.Model.AnunciosModel;
import backend.Repository.IAnunciosRepository;

@Service
public class AnuncioServiceImp implements IAnuncioService{
    
    @Autowired
    IAnunciosRepository anunciosRepository;

    @Override
    public AnunciosModel crearAnuncio(AnunciosModel anuncio) {
        AnunciosModel anuncioRegistrado = anunciosRepository.save(anuncio);
        System.out.println(anuncioRegistrado);
        return anuncioRegistrado;
    }

    @Override
    public List<AnunciosModel> listarAnuncios() {
        return anunciosRepository.findAll();
    }

    @Override
    public AnunciosModel buscarAnuncioPorId(ObjectId id) {
        return anunciosRepository.findById(id).orElse(null);
    }
}
