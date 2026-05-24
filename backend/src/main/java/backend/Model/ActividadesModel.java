package backend.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "Actividades")
@NoArgsConstructor
@AllArgsConstructor
public class ActividadesModel {
    @Id
    private ObjectId id;

    @NotBlank(message = "El nombre de la actividad es obligatorio")
    private String nombreActividad;

    @NotBlank(message = "La categoría es obligatoria")
    private enum categoria{Arte, Deporte, Tecnologia, Salud, Emprendimiento, Desarrollo_Personal};

    @NotBlank
    private categoria categoria;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotBlank(message = "El objetivo es obligatorio")
    private String objetivo;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    private LocalDate fechaFin;

    @Min(value = 1, message = "La intensidad horaria debe ser al menos 1")
    private int intensidadHoraria;

    @NotNull(message = "El cupo máximo es obligatorio")
    @Min(value = 1, message = "El cupo máximo debe estar minimo 1")
    @Max(value = 100, message = "El cupo máximo debe estar hasta 100")
    private Integer cupoMaximo;

    @NotNull(message = "El estado de la actividad es obligatorio")
    public enum EstadoActividad{Programada, En_Curso, Finalizada, Cancelada, Propuesta};

    @NotNull
    private EstadoActividad EstadoActividad;

    @Valid
    @NotEmpty(message = "Debe registrar al menos un recurso para la actividad")
    private List<RecursoItem> recursos = new ArrayList<>();

    @NotNull(message = "Debe indicarse quién propone la actividad")
    private ObjectId propuestoPor;

    private LocalDate fechaAprobacion;

    private ObjectId aprobadoPor;

    private ObjectId impartidoPor;

    private List<InscripcionActividad> inscripciones = new ArrayList<>();

    @Valid
    private List<EvaluacionActividad> evaluaciones = new ArrayList<>();

    private ObjectId programaId;

    private List<SeguimientoActividad> seguimientos = new ArrayList<>();

    @JsonSetter("propuestoPor")
    public void setPropuestoPorJson(String propuestoPor) {
        this.propuestoPor = (propuestoPor == null) ? null : new ObjectId(propuestoPor);
    }

    @JsonSetter("aprobadoPor")
    public void setAprobadoPorJson(String aprobadoPor) {
        this.aprobadoPor = (aprobadoPor == null) ? null : new ObjectId(aprobadoPor);
    }

    @JsonSetter("impartidoPor")
    public void setImpartidoPorJson(String impartidoPor) {
        this.impartidoPor = (impartidoPor == null) ? null : new ObjectId(impartidoPor);
    }

    @JsonSetter("programaId")
    public void setProgramaIdJson(String programaId) {
        this.programaId = (programaId == null) ? null : new ObjectId(programaId);
    }

    @JsonProperty("id")
    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }

    @JsonProperty("propuestoPor")
    public String getPropuestoPorAsString() {
        return propuestoPor != null ? propuestoPor.toHexString() : null;
    }

    @JsonProperty("aprobadoPor")
    public String getAprobadoPorAsString() {
        return aprobadoPor != null ? aprobadoPor.toHexString() : null;
    }

    @JsonProperty("impartidoPor")
    public String getImpartidoPorAsString() {
        return impartidoPor != null ? impartidoPor.toHexString() : null;
    }

    @JsonProperty("programaId")
    public String getProgramaIdAsString() {
        return programaId != null ? programaId.toHexString() : null;
    }
}
