package backend.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import backend.Model.ComentariosModel;


public interface IComentariosRepository extends MongoRepository<ComentariosModel, ObjectId> {

    java.util.List<ComentariosModel> buscarPorForoId (ObjectId foroId);

    long countByForoId(ObjectId foroId);
}
