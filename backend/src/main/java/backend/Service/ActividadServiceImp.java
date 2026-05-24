package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.Model.ActividadesModel;
import backend.Model.EvaluacionActividad;
import backend.Model.InscripcionActividad;
import backend.Model.UsuariosModel;
import backend.Repository.IActividadesRepository;

@Service
public class ActividadServiceImp implements IActividadService{
    @Autowired
    IActividadesRepository actividadesRepository;

    @Autowired
    IUsuarioService usuarioService;

    @Override
    public ActividadesModel crearActividad(ActividadesModel actividad) {
        
        if(actividad.getFechaFin().isBefore(actividad.getFechaInicio())){
            throw new IllegalArgumentException("La fecha Fin no puede ser anterio o inferior a la fecha de inicio.");
        }

        UsuariosModel proponente = usuarioService.buscarUsuarioPorId(actividad.getPropuestoPor());

        if(proponente == null){
            throw new IllegalArgumentException("No existe el usuario que propone la actividad.");
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

            String rolAprobador = aprobador.getRol().name();

            if (!"Administrador".equals(rolAprobador) && !"Coordinador".equals(rolAprobador)) {
                throw new IllegalArgumentException("Solo administradores o coordinadores pueden aprobar actividades.");
            }
        }

        if ("Propuesta".equals(estado) && actividad.getAprobadoPor() != null) {
            throw new IllegalArgumentException("Una actividad en estado Propuesta no puede tener aprobador hasta completar la revisión administrativa.");
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
}
