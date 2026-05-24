package backend.Model;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionActividad {
    @NotNull(message = "El participante es obligatorio")
    private ObjectId participanteId;

    @NotBlank(message = "El nombre del parcipante es obligatorio")
    private String nombre;

    @NotNull(message = "La valoracion numerica es obligatoria")
    @Min(value = 0, message = "La valoracion numerica debe estar entre 0 y 10")
    @Max(value = 10, message = "La valoracion numerica debe estar entre 0 y 10")
    private Integer valoracionNumerica;

    @NotBlank
    private String observaciones;

    @NotBlank
    private String sugerencias;

    @JsonSetter("participanteId")
    public void setParticipanteIdJson(String participanteId) {
    this.participanteId = (participanteId == null) ? null : new ObjectId(participanteId);
    }
}
