package backend.Model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchivoSesion {
    
    @NotBlank
    private String nombre;

    @NotBlank
    private String tipo;

    @NotBlank
    private String URL;

    @NotBlank
    private String descripcion;
}
