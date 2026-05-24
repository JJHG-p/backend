package backend.Model;

import java.time.LocalDate;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionActividad {
    @NotNull(message = "El participante es Obligatorio")
    private ObjectId participanteId;

    @NotNull(message = "El nombre del participante es obligatorio")
    private String nombreParticipante;

    @NotNull(message = "La fecha de inscripción del participante es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "El estado de la inscripcion del participante es obligatoria")
    private enum estado {Inscrito, Matriculado, Retirado}

    @NotNull
    private estado estado;

    @JsonSetter("participanteId")
    public void setParticipanteIdJson(String participanteId) {
    this.participanteId = (participanteId == null) ? null : new ObjectId(participanteId);
    }
}
