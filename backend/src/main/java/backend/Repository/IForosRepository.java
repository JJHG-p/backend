package backend.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import backend.Model.ForosModel;

public interface IForosRepository extends MongoRepository<ForosModel, ObjectId> {

    java.util.List<ForosModel> findByEstado(ForosModel.Estado estado);
}
