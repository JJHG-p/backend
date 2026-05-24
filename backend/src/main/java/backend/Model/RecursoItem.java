package backend.Model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecursoItem {
    
    @NotBlank(message = "El tipo de recurso es obligatorio")
    private String tipo;

    @NotBlank(message = "El nombre del recurso es obligatorio")
    private String nombre;
}
