package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.Model.ActividadesModel;
import backend.Model.EvaluacionActividad;
import backend.Model.InscripcionActividad;
import backend.Model.ProgramasModel;
import backend.Model.UsuariosModel;
import backend.Repository.IActividadesRepository;
import backend.Repository.IProgramasRepository;

@Service
public class ActividadServiceImp implements IActividadService{
    @Autowired
    IActividadesRepository actividadesRepository;

    @Autowired
    IUsuarioService usuarioService;

    @Autowired
    IProgramasRepository programasRepository;

    @Override
    public ActividadesModel crearActividad(ActividadesModel actividad) {
        
        if(actividad.getFechaFin().isBefore(actividad.getFechaInicio())){
            throw new IllegalArgumentException("La fecha Fin no puede ser anterio o inferior a la fecha de inicio.");
        }

        UsuariosModel proponente = usuarioService.buscarUsuarioPorId(actividad.getPropuestoPor());

        if(proponente == null){
            throw new IllegalArgumentException("No existe el usuario que propone la actividad.");
        }

        if (!proponente.isActivo()) {
            throw new IllegalArgumentException("El usuario que propone la actividad debe estar activo en el sistema.");
        }

        String rolProponente = proponente.getRol().name();

        if(!"Instructor".equals(rolProponente) && !"Coordinador".equals(rolProponente) && !"Administrador".equals(rolProponente)){
            throw new IllegalArgumentException("Solo el personal del centro o líderes autorizados pueden proponer actividades.");
        }

        if(actividad.getImpartidoPor() != null){
            UsuariosModel instructor = usuarioService.buscarUsuarioPorId(actividad.getImpartidoPor());

            if(instructor == null){
                throw new IllegalArgumentException("No existe el usuario que imparte la actividad.");
            }

            if (!instructor.isActivo()) {
                throw new IllegalArgumentException("El usuario que imparte la actividad debe estar activo en el sistema.");
            }

            String rolInstructor = instructor.getRol().name();

            if (!"Instructor".equals(rolInstructor) && !"Coordinador".equals(rolInstructor)) {
                throw new IllegalArgumentException("Solo instructores o coordinadores pueden impartir actividades.");
            }
        }

        String estado = actividad.getEstado().name();

        if (("Programada".equals(estado) || "En_Curso".equals(estado))) {
            if (actividad.getFechaAprobacion() == null) {
                throw new IllegalArgumentException("La actividad debe tener fecha de aprobación administrativa antes de publicarse.");
            }
            if (actividad.getAprobadoPor() == null) {
                throw new IllegalArgumentException("Debe registrarse quién aprobó la actividad.");
            }

            UsuariosModel aprobador = usuarioService.buscarUsuarioPorId(actividad.getAprobadoPor());

            if (aprobador == null) {
                throw new IllegalArgumentException("No existe el usuario que aprueba la actividad.");
            }

            if (!aprobador.isActivo()) {
                throw new IllegalArgumentException("El usuario que aprueba la actividad debe estar activo en el sistema.");
            }

            String rolAprobador = aprobador.getRol().name();

            if (!"Administrador".equals(rolAprobador) && !"Coordinador".equals(rolAprobador)) {
                throw new IllegalArgumentException("Solo administradores o coordinadores pueden aprobar actividades.");
            }
        }

        if ("Propuesta".equals(estado) && actividad.getAprobadoPor() != null) {
            throw new IllegalArgumentException("Una actividad en estado Propuesta no puede tener aprobador hasta completar la revisión administrativa.");
        }

        if ("Finalizada".equals(estado) && actividad.getInscripciones() != null && !actividad.getInscripciones().isEmpty()) {
            throw new IllegalArgumentException("No se pueden agregar participantes a una actividad en estado Finalizada.");
        }

        if ("Finalizada".equals(estado) && actividad.getEvaluaciones() != null && !actividad.getEvaluaciones().isEmpty()) {
            throw new IllegalArgumentException("No se pueden agregar evaluaciones a una actividad en estado Finalizada.");
        }

        if (actividad.getEvaluaciones() != null) {
            for (EvaluacionActividad evaluacion : actividad.getEvaluaciones()){
                validarEvaluacion(evaluacion);
            }
        }

        if (actividad.getInscripciones() != null) {
            for (InscripcionActividad inscripcion : actividad.getInscripciones()){
                UsuariosModel participante = usuarioService.buscarUsuarioPorId(inscripcion.getParticipanteId());

                if (participante == null) {
                    throw new IllegalArgumentException("El participante inscrito no existe.");
                }

                if (!"Participante".equals(participante.getRol().name())) {
                    throw new IllegalArgumentException("Solo los participantes pueden inscribirse en actividades.");
                }

                if (!participante.isActivo()) {
                    throw new IllegalArgumentException("El participante inscrito debe estar activo en el sistema.");
                }
            }
        }

        boolean existeActividad = actividadesRepository.existsByNombreActividad(actividad.getNombreActividad());

        if (existeActividad) {
            throw new IllegalArgumentException("La actividad ya existe.");
        }

        return actividadesRepository.save(actividad);
    }

    private void validarEvaluacion(EvaluacionActividad evaluacion){

            UsuariosModel participante = usuarioService.buscarUsuarioPorId(evaluacion.getParticipanteId());

            if (participante == null) {
                throw new IllegalArgumentException("El participante que evalúa no existe.");
            }

            if (!"Participante".equals(participante.getRol().name())) {
                throw new IllegalArgumentException("Solo los participantes pueden evaluar actividades.");
            }

            if (!participante.isActivo()) {
                throw new IllegalArgumentException("El participante que evalúa debe estar activo en el sistema.");
            }
        }

    @Override
    public List<ActividadesModel> listarActividades() {
        return actividadesRepository.findAll();
    }

    @Override
    public ActividadesModel buscarActividadPorId(ObjectId id) {
        return actividadesRepository.findById(id).orElse(null);
    }

    @Override
    public ActividadesModel obtenerActividadPorId(ObjectId id){
        return buscarActividadPorId(id);
    }

    @Override
    public ActividadesModel actualizarActividad(ObjectId id, ActividadesModel actividadActualizada){
        ActividadesModel actividadExistente = actividadesRepository.findById(id).orElse(null);
        if (actividadExistente == null) {
            throw new IllegalArgumentException("La actividad no existe.");
        }

        if ("Finalizada".equals(actividadExistente.getEstado().name())) {
            if (actividadActualizada.getInscripciones() != null && !actividadActualizada.getInscripciones().equals(actividadExistente.getInscripciones())) {
                throw new IllegalArgumentException("No se pueden modificar participantes de una actividad Finalizada.");
            }

            if (actividadActualizada.getEvaluaciones() != null && !actividadActualizada.getEvaluaciones().equals(actividadExistente.getEvaluaciones())) {
                throw new IllegalArgumentException("No se pueden modificar evaluaciones de una actividad Finalizada.");
            }
        }

        String estadoActualizado = actividadActualizada.getEstado().name();

        if ("Finalizada".equals(estadoActualizado) && actividadActualizada.getInscripciones() != null && !actividadActualizada.getInscripciones().isEmpty()) {
            throw new IllegalArgumentException("No se pueden agregar participantes a una actividad en estado Finalizada.");
        }

        if ("Finalizada".equals(estadoActualizado) && actividadActualizada.getEvaluaciones() != null && !actividadActualizada.getEvaluaciones().isEmpty()) {
            throw new IllegalArgumentException("No se pueden agregar evaluaciones a una actividad en estado Finalizada.");
        }

        if (actividadActualizada.getInscripciones() != null) {
            for (InscripcionActividad inscripcion : actividadActualizada.getInscripciones()) {
                UsuariosModel participante = usuarioService.buscarUsuarioPorId(inscripcion.getParticipanteId());

                if (participante == null) {
                    throw new IllegalArgumentException("El participante inscrito no existe.");
                }

                if (!"Participante".equals(participante.getRol().name())) {
                    throw new IllegalArgumentException("Solo los participantes pueden inscribirse en actividades.");
                }

                if (!participante.isActivo()) {
                    throw new IllegalArgumentException("El participante inscrito debe estar activo en el sistema.");
                }
            }
        }

        if (actividadActualizada.getEvaluaciones() != null) {
            for (EvaluacionActividad evaluacion : actividadActualizada.getEvaluaciones()) {
                validarEvaluacion(evaluacion);
            }
        }

        actividadActualizada.setId(id);

        ActividadesModel actividadGuardada = actividadesRepository.save(actividadActualizada);

        actualizarActividadEnProgramas(actividadGuardada);

        return actividadGuardada;
    }

    private void actualizarActividadEnProgramas(ActividadesModel actividad) {
        List<ProgramasModel> programas = programasRepository.findAll();

        for (ProgramasModel programa : programas) {
            boolean actualizado = false;
            if (programa.getActividades() != null) {
                for (var actividadPrograma : programa.getActividades()){
                    if (actividadPrograma.getActividadId().equals(actividad.getId())) {
                        actividadPrograma.setNombreActividad(actividad.getNombreActividad());

                        actualizado = true;
                    }
                }
            }

            if (actualizado) {
                programasRepository.save(programa);
            }
        }
    }
}
