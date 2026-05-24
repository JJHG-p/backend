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
public class AsistenciaSesion {
    
    @NotNull
    private ObjectId usuarioId;

    @NotBlank
    private String nombreUsuario;

    @NotNull
    private Boolean estado;

    @NotBlank
    private String observacion;
}
