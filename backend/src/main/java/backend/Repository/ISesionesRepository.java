package backend.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import backend.Model.SesionesModel;

public interface ISesionesRepository extends MongoRepository<SesionesModel, ObjectId>{
    
    java.util.List<SesionesModel> findByActividadId(ObjectId actividadId);

    java.util.List<SesionesModel> findByEspacioIdAndFecha(ObjectId espacioId, java.time.LocalDate fecha);

    boolean existsByActividadIdAndFechaAndHoraInicio(ObjectId actividadId, LocalDate fecha, LocalTime horaInicio);
}
