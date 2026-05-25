package backend.Model;

import java.time.LocalDate;

import org.bson.types.ObjectId;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private LocalDate fechaInscripcion;
}
