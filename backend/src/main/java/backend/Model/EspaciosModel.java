package backend.Model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "espacios")
@NoArgsConstructor
@AllArgsConstructor
public class EspaciosModel {
    @Id
    private ObjectId id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String tipo;

    @NotNull
    @Min(value = 1)
    private Integer capacidad;

    @NotBlank
    private String ubicacion;

    @NotNull
    private Boolean disponible;

    private List<EspacioRecurso> recursos;

    @JsonProperty("id")
    public String getIdAsString(){
        return id != null ? id.toHexString() : null;
    }
}
