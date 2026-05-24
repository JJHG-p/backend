package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.Model.ComentariosModel;
import backend.Repository.IComentariosRepository;

@Service
public class ComentarioServiceImp implements IComentarioService{
    
    @Autowired
    IComentariosRepository comentariosRepository;

    @Override
    public ComentariosModel crearComentario(ComentariosModel comentario) {
        ComentariosModel comentarioRegistrado = comentariosRepository.save(comentario);
        System.out.println(comentarioRegistrado);
        return comentarioRegistrado;
    }

    @Override
    public List<ComentariosModel> listarComentarios() {
        return comentariosRepository.findAll();
    }

    @Override
    public ComentariosModel buscarComentarioPorId(ObjectId id) {
        return comentariosRepository.findById(id).orElse(null);
    }
}
