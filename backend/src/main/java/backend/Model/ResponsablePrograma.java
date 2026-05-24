package backend.Model;

import org.bson.types.ObjectId;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponsablePrograma {
    
    @NotBlank
    private ObjectId responsableId;

    @NotBlank
    private String nombreResponsable;
    
}
