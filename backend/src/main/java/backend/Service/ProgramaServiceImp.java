package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.Model.ProgramasModel;
import backend.Repository.IProgramasRepository;

@Service
public class ProgramaServiceImp implements IProgramaService{
    
    @Autowired
    IProgramasRepository programasRepository;

    @Override
    public ProgramasModel crearPrograma(ProgramasModel programa) {
        ProgramasModel programaRegistrado = programasRepository.save(programa);
        System.out.println(programaRegistrado);
        return programaRegistrado;
    }

    @Override
    public List<ProgramasModel> listarProgramas() {
        return programasRepository.findAll();
    }

    @Override
    public ProgramasModel buscarProgramaPorId(ObjectId id) {
        return programasRepository.findById(id).orElse(null);
    }
}
