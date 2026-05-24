package backend.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import backend.Model.ActividadesModel;

public interface IActividadesRepository extends MongoRepository<ActividadesModel, ObjectId>{
    
    boolean existsByNombreActividad(String nombreActividad);

}
