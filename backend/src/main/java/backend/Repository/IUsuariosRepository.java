package backend.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import backend.Model.UsuariosModel;

public interface IUsuariosRepository extends MongoRepository<UsuariosModel, ObjectId>{
    
    boolean existePorDocumentoID(String documentoID);

    boolean existsByEmailIgnoreCase(String email);
}
