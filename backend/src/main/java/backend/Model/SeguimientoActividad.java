package backend.Model;

import org.bson.types.ObjectId;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoActividad {
    
    @NotNull(message = "El participante es obligatorio")
    public ObjectId participanteId;

    @NotBlank
    public String comentarios;

    @NotBlank
    public String aspectosEvaluados;

    @NotBlank
    public String nivelProgreso;

    @NotBlank
    private String observaciones;
}
