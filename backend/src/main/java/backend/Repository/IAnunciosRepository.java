package backend.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import backend.Model.AnunciosModel;

public interface IAnunciosRepository extends MongoRepository<AnunciosModel, ObjectId> {

    java.util.List<AnunciosModel> findByActividadId(ObjectId actividaId);

    java.util.List<AnunciosModel> findByCreadorId(ObjectId creadorId);
}
