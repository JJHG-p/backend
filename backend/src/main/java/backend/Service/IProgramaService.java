package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import backend.Model.ProgramasModel;

public interface IProgramaService {
    ProgramasModel crearPrograma(ProgramasModel programa);
    List<ProgramasModel> listarProgramas();
    ProgramasModel buscarProgramaPorId(ObjectId id);
}
