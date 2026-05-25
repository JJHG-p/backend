package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.Model.ProgramasModel;
import backend.Model.ResponsablePrograma;
import backend.Model.UsuariosModel;
import backend.Repository.IProgramasRepository;

@Service
public class ProgramaServiceImp implements IProgramaService{
    
    @Autowired
    IProgramasRepository programasRepository;

    @Autowired
    IUsuarioService usuarioService;

    @Override
    public ProgramasModel crearPrograma(ProgramasModel programa) {
        if (programa.getFechaFin().isBefore(programa.getFechaInicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }

        for (ResponsablePrograma responsable : programa.getResponsables()) {
            UsuariosModel usuario = usuarioService.buscarUsuarioPorId(responsable.getResponsableId());
            if (usuario == null) {
                throw new IllegalArgumentException("El responsable del programa no existe.");
            }

            String rol = usuario.getRol().name();
            if (!"Coordinador".equals(rol) && !"Administrador".equals(rol)) {
                throw new IllegalArgumentException("Solo coordinadores o administradores pueden ser responsables de un programa.");
            }
        }

        if (programa.getInscritos() != null) {
            programa.getInscritos().forEach(inscrito -> {
                UsuariosModel usuario = usuarioService.buscarUsuarioPorId(inscrito.getParticipanteId());

                if (usuario == null) {
                    throw new IllegalArgumentException("El usuario inscrito al programa no existe.");
                }

                if (!usuario.isActivo()) {
                    throw new IllegalArgumentException("El usuario inscrito debe estar activo en el sistema.");
                }
            });
        }

        return programasRepository.save(programa);
    }

    @Override
    public List<ProgramasModel> listarProgramas() {
        return programasRepository.findAll();
    }

    @Override
    public ProgramasModel buscarProgramaPorId(ObjectId id) {
        return programasRepository.findById(id).orElse(null);
    }

    @Override
    public ProgramasModel actualizarPrograma (ObjectId id, ProgramasModel programaActualizado) {
        ProgramasModel programaExistente = programasRepository.findById(id).orElse(null);

        if (programaExistente == null) {
            throw new IllegalArgumentException("El programa no existe.");
        }

        programaActualizado.setId(id);

        return programasRepository.save(programaActualizado);
    }
}
