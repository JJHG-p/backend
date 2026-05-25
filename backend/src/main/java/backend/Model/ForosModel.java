package backend.Model;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "foros")
@NoArgsConstructor
@AllArgsConstructor
public class ForosModel {
    @Id
    private ObjectId id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String contenido;

    @NotNull
    private ObjectId creadorId;

    @NotNull
    private LocalDate fechaPublicacion;

    @NotBlank
    public enum Estado {Abierto, Cerrado, Oculto}

    @NotNull
    private Estado estado;

    private List<ArchivoAdjunto> archivosAdjuntos;


    @JsonProperty("creadorId")
    public void setCreadorIdJson(String creadorId){
        this.creadorId = (creadorId == null) ? null : new ObjectId(creadorId);
    }

    @JsonProperty("id")
    public String getIdAsString(){
        return id != null ? id.toHexString() : null;
    }

    @JsonProperty("creadorId")
    public String getCreadorIdAsString(){
        return creadorId != null ? creadorId.toHexString() : null;
    }
}