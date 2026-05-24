package backend.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import backend.Model.AnunciosModel;

public interface IAnunciosRepository extends MongoRepository<AnunciosModel, ObjectId> {

    java.util.List<AnunciosModel> buscarPorActividadId(ObjectId actividaId);

    java.util.List<AnunciosModel> buscarPorCreadorId(ObjectId creadorId);
}
