package backend.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspacioRecurso {
    
    @NotBlank
    private enum tipo {Inmobiliario, Tecnologico}

    @NotNull
    private tipo tipo;

    @NotBlank
    private String nombre;
}
