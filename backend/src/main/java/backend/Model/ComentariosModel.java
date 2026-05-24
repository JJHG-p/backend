package backend.Model;

import java.time.LocalDate;
import java.util.List;

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
@Document(collection = "comentarios")
@NoArgsConstructor
@AllArgsConstructor
public class ComentariosModel {
    @Id
    private ObjectId id;

    @NotNull
    private ObjectId foroId;

    @NotNull
    private ObjectId usuarioId;

    @NotBlank
    private String contenido;

    @NotBlank
    private LocalDate fechaPublicacion;

    private List<ArchivoAdjunto> ArchivosAdjuntos;

    private List<ReplicaComentario> ReplicasComentarios;

    @JsonSetter("foroId")
    public void setForoIdJson(String foroId) {
        this.foroId = (foroId == null) ? null : new ObjectId(foroId);
    }

    @JsonSetter("usuarioId")
    public void setUsuarioIdJson(String usuarioId) {
        this.usuarioId = (usuarioId == null) ? null : new ObjectId(usuarioId);
    }

    @JsonProperty("id")
    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }

    @JsonProperty("foroId")
    public String getForoIdAsString() {
        return foroId != null ? foroId.toHexString() : null;
    }

    @JsonProperty("usuarioId")
    public String getUsuarioIdAsString() {
        return usuarioId != null ? usuarioId.toHexString() : null;
    }
}
