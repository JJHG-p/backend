package backend.Model;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplicaComentario {
    @NotNull
    private ObjectId usuarioId;

    @NotBlank
    private String contenido;

    @NotBlank
    private LocalDate fechaPublicacion;

    private List<ArchivoAdjunto> archivosAdjuntos;
}
