package backend.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import backend.Model.ProgramasModel;

public interface IProgramasRepository extends MongoRepository<ProgramasModel, ObjectId> {

    java.util.List<ProgramasModel> buscarPorResponsableId(ObjectId responsableId);
}
