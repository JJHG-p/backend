package backend.Model;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "anuncios")
@NoArgsConstructor
@AllArgsConstructor
public class AnunciosModel {
    @Id
    private ObjectId id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String contenido;

    @NotBlank
    private LocalDate fechaPublicacion;

    @NotNull
    private ObjectId creadorId;

    @NotNull
    private ObjectId actividadId;

    @JsonSetter("creadorId")
    public void setCreadorIdJson(String creadorId) {
        this.creadorId = (creadorId == null) ? null : new ObjectId(creadorId);
    }

    @JsonSetter("actividadId")
    public void setActividadIdJson(String actividadId) {
        this.actividadId = (actividadId == null) ? null : new ObjectId(actividadId);
    }

    @JsonProperty("id")
    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }

    @JsonProperty("creadorId")
    public String getCreadorIdAsString() {
        return creadorId != null ? creadorId.toHexString() : null;
    }

    @JsonProperty("actividadId")
    public String getActividadIdAsString() {
        return actividadId != null ? actividadId.toHexString() : null;
    }
}
