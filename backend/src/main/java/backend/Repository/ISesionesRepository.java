package backend.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import backend.Model.SesionesModel;

public interface ISesionesRepository extends MongoRepository<SesionesModel, ObjectId>{
    
    java.util.List<SesionesModel> buscarPorActividadId(ObjectId actividadId);

    java.util.List<SesionesModel> buscarPorEspacioIdYFecha(ObjectId espacioId, java.time.LocalDate fecha);
}
