package backend.Model;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosModel {
    @Id
    private ObjectId id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El documento es obligatorio")
    @Indexed(unique = true)
    private String documentoID;

    @Min(0)
    @Max(120)
    private int edad;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un correo válido")//Validación adicional
    @Indexed(unique = true)
    private String email;

    @NotBlank(message = "El telefono es obligatorio")
    @Size(min=7, max = 15)
    private String telefono;

    @NotBlank
    private String residencia;

    @NotNull(message = "El rol es obligatorio")
    public enum rol{Participante, Instructor, Coordinador, Administrador };

    @NotNull(message = "El rol es obligatorio")
    private rol rol;

    private LocalDate fechaRegistro;

    private boolean activo = true;

    @JsonProperty("id")
    private String getIdAsString(){
        return id != null ? id.toHexString() : null;
    }
}
