package backend.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "sesiones")
@NoArgsConstructor
@AllArgsConstructor
public class SesionesModel {
    @Id
    private ObjectId id;

    @NotNull
    private ObjectId actividaId;

    @NotNull(message = "La fecha de la sesión es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria")
    private LocalTime horaFin;

    public enum modalidad {Presencial, Virtual, Hibrida}

    @NotNull
    private modalidad modalidad;

    private ObjectId espacioId;

    private String enlace;

    private List<AsistenciaSesion> asistencias;

    private List<ArchivoSesion> archivosAdjuntos;

    @JsonProperty("id")
    public String getIdAsString(){
        return id != null ? id.toHexString() : null;
    }

    @JsonProperty("actividadId")
    public void setActividadIdJson(String actividadId){
        this.actividaId = (actividaId == null) ? null : new ObjectId(actividadId);
    }

    @JsonProperty("actividadId")
    public String getActividadIdJson(){
        return actividaId != null ? actividaId.toHexString() : null;
    }

    @JsonProperty("espacioId")
    public void setEspacioIdJson(String espacioId){
        this.espacioId = (espacioId == null) ? null : new ObjectId(espacioId);
    }

    @JsonProperty("espacioId")
    public String getEspacioIdJson(){
        return espacioId != null ? espacioId.toHexString() : null;
    }
}
