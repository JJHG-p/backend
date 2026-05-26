package backend.Service;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.Model.ActividadesModel;
import backend.Model.ProgramasModel;
import backend.Model.SesionesModel;
import backend.Model.UsuariosModel;
import backend.Repository.IActividadesRepository;
import backend.Repository.IProgramasRepository;
import backend.Repository.ISesionesRepository;
import backend.Repository.IUsuariosRepository;

@Service
public class UsuarioServiceImp implements IUsuarioService {
    @Autowired 
    IUsuariosRepository usuariosRepository;

    @Autowired
    IActividadesRepository actividadesRepository;

    @Autowired
    IProgramasRepository programasRepository;

    @Autowired
    ISesionesRepository sesionesRepository;

    @Override
    public UsuariosModel crearUsuario(UsuariosModel usuario){
        if (usuariosRepository.existsByDocumentoID(usuario.getDocumentoID())) {
            throw new IllegalArgumentException("Ya existe un usuario con el documento: " + usuario.getDocumentoID());
        }

        if (usuariosRepository.existsByEmailIgnoreCase(usuario.getEmail().trim())) {
            throw new IllegalArgumentException("Ya existe un usuario con el email: " + usuario.getEmail());
        }

        if (usuario.getFechaRegistro() == null) {
            usuario.setFechaRegistro(LocalDate.now());
        }

        usuario.setActivo(true);

        return usuariosRepository.save(usuario);
    }

    @Override
    public List<UsuariosModel> listarUsuarios(){
        return usuariosRepository.findAll();
    }

    @Override
    public UsuariosModel buscarUsuarioPorId(ObjectId id){
        return usuariosRepository.findById(id).orElse(null);
    }

    @Override
    public UsuariosModel actualizarUsuario(ObjectId id, UsuariosModel usuarioActualizado) {
        UsuariosModel usuarioExistente = usuariosRepository.findById(id).orElse(null);

        if (usuarioExistente == null) {
            throw new IllegalArgumentException("El usuario no existe.");
        }

        usuarioActualizado.setId(id);

        UsuariosModel usuarioGuardado = usuariosRepository.save(usuarioActualizado);

        actualizarUsuarioEnActividades (usuarioGuardado);

        actualizarUsuarioEnProgramas (usuarioGuardado);
        
        actualizarUsuarioEnSesiones (usuarioGuardado);

        return usuarioGuardado;
    }

    private void actualizarUsuarioEnActividades(UsuariosModel usuario) {
        List<ActividadesModel> actividades = actividadesRepository.findAll();

        for (ActividadesModel actividad : actividades) {
            boolean actualizado = false;

            if (actividad.getInscripciones() != null) {
                for (var inscripcion : actividad.getInscripciones()) {
                    if (inscripcion.getParticipanteId().equals(usuario.getId())) {
                        inscripcion.setNombreParticipante(usuario.getNombre());

                        actualizado = true;
                    }
                }
            }

            if (actividad.getEvaluaciones() != null) {
                for (var evaluacion : actividad.getEvaluaciones()) {
                    if (evaluacion.getParticipanteId().equals(usuario.getId())) {
                        evaluacion.setNombre(usuario.getNombre());

                        actualizado = true;
                    }
                }
            }

            if (actualizado) {
                actividadesRepository.save(actividad);
            }
        }
    }

    private void actualizarUsuarioEnProgramas (UsuariosModel usuario) {
        List<ProgramasModel> programas = programasRepository.findAll();

        for (ProgramasModel programa : programas) {
            boolean actualizado = false;

            if (programa.getResponsables() != null) {
                for (var responsable : programa.getResponsables()) {
                    if (responsable.getResponsableId().equals(usuario.getId())) {
                        responsable.setNombreResponsable(usuario.getNombre());

                        actualizado = true;
                    }
                }
            }

            if (programa.getInscritos() != null) {
                for (var inscrito : programa.getInscritos()) {
                    if (inscrito.getParticipanteId().equals(usuario.getId())) {
                        inscrito.setNombreParticipante(usuario.getNombre());
                        actualizado = true;
                    }
                }
            }

            if (actualizado) {
                programasRepository.save(programa);
            }
        }
    }

    private void actualizarUsuarioEnSesiones (UsuariosModel usuario) {
        List<SesionesModel> sesiones = sesionesRepository.findAll();

        for (SesionesModel sesion : sesiones) {
            boolean actualizado = false;

            if (sesion.getAsistencias() != null) {
                for (var asistencia : sesion.getAsistencias()) {
                    if (asistencia.getUsuarioId().equals(usuario.getId())) {
                        asistencia.setNombreUsuario(usuario.getNombre());

                        actualizado = true;
                    }
                }
            }

            if (actualizado) {
                sesionesRepository.save(sesion);
            }
        }
    }
}
