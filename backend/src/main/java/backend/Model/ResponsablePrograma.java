package backend.Model;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.mongodb.lang.NonNull;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsablePrograma {
    
    @NonNull
    private ObjectId responsableId;

    @NotBlank
    private String nombreResponsable;
    
    @JsonSetter("responsableId")
    public void setResponsableIdJson(String responsableId) {
    this.responsableId = (responsableId == null) ? null : new ObjectId(responsableId);
    }
}
