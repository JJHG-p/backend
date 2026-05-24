package backend.Model;

import org.bson.types.ObjectId;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActividadEnPrograma {
    
    @NotBlank
    private ObjectId actividadId;

    @NotBlank
    private String nombreActividad;
}
