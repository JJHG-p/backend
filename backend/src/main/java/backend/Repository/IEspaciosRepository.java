package backend.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import backend.Model.EspaciosModel;

public interface IEspaciosRepository extends MongoRepository<EspaciosModel, ObjectId> {

    java.util.List<EspaciosModel> buscarPorDisponibleTrue();
}
