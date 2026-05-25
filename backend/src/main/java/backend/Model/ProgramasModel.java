package backend.Model;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "programas")
@NoArgsConstructor
@AllArgsConstructor
public class ProgramasModel {
    @Id
    private ObjectId id;

    @NotBlank
    private enum tipo {Jornada_de_salud, Feria_de_emprendimiento, Semana_cultural, Ciclos_de_capacitación}

    @NotNull
    private tipo tipo;

    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaFin;

    @NotBlank
    private String poblacionObjetivo;

    @NotNull
    private List<ResponsablePrograma> responsables;

    private List<ActividadEnPrograma> actividades;

    private List<InscritoPrograma> inscritos;

    @JsonProperty("id")
    public String getIdAsString(){
        return id != null ? id.toHexString() : null;
    }
}
