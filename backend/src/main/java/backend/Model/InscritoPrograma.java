package backend.Model;

import java.time.LocalDate;

import org.bson.types.ObjectId;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscritoPrograma {
    @NotBlank
    private ObjectId participanteId;

    @NotBlank
    private String nombreParticipante;

    @NotBlank
    private LocalDate fechaInscripcion;
}
