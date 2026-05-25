package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.Model.ActividadesModel;
import backend.Model.EspaciosModel;
import backend.Model.SesionesModel;
import backend.Model.UsuariosModel;
import backend.Repository.ISesionesRepository;

@Service
public class SesionServiceImp implements ISesionService{
    @Autowired
    ISesionesRepository sesionesRepository;

    @Autowired
    IActividadService actividadService;

    @Autowired
    IEspacioService espacioService;

    @Autowired
    IUsuarioService usuarioService;

    @Override
    public SesionesModel crearSesion(SesionesModel sesion) {
        ActividadesModel actividad = actividadService.buscarActividadPorId(sesion.getActividadId());

        if (actividad == null) {
            throw new IllegalArgumentException("La actividad con el ID proporcionado no existe.");
        }

        if (!sesion.getHoraFin().isAfter(sesion.getHoraInicio())) {
            throw new IllegalArgumentException("La hora de fin no puede ser anterior o igual a la hora de inicio.");
        }

        String modalidad = sesion.getModalidad().name();

        if ("Presencial".equals(modalidad) || "Hibrida".equals(modalidad)) {
            if (sesion.getEspacioId() == null) {
                throw new IllegalArgumentException("Las sesiones Presenciales e Híbridas requieren un espacio físico.");
            }

            EspaciosModel espacio = espacioService.buscarEspacioPorId(sesion.getEspacioId());

            if (espacio == null) {
                throw new IllegalArgumentException("El espacio con el ID proporcionado no existe.");
            }

            if (!Boolean.TRUE.equals(espacio.getDisponible())) {
                throw new IllegalArgumentException("El espacio seleccionado no está disponible para reserva.");
            }

            if (actividad.getCupoMaximo() != null && espacio.getCapacidad() < actividad.getCupoMaximo()) {
                throw new IllegalArgumentException("La capacidad del espacio debe ser mayor o igual al cupo máximo de la actividad.");
            }
        }

        if ("Virtual".equals(modalidad) || "Hibrida".equals(modalidad)) {
            if (sesion.getEnlace() == null || sesion.getEnlace().isBlank()) {
                throw new IllegalArgumentException("Las sesiones Virtuales e Híbridas requieren un enlace de acceso.");
            }
        }

        if ("Presencial".equals(modalidad) && sesion.getEnlace() != null && !sesion.getEnlace().isBlank()) {
            throw new IllegalArgumentException("Las sesiones Presenciales no deben incluir enlace Virtual.");
        }

        if ("Virtual".equals(modalidad) && sesion.getEspacioId() != null) {
            throw new IllegalArgumentException("Las sesiones Virtuales no deben asignar un espacio fisico.");
        }

        if (sesion.getAsistencias() != null) {
            for (var asistencia : sesion.getAsistencias()) {
                UsuariosModel participante = usuarioService.buscarUsuarioPorId(asistencia.getUsuarioId());
                if (participante == null) {
                    throw new IllegalArgumentException("El participante en la asistencia no existe.");
                }
                if (!"Participante".equals(participante.getRol().name())) {
                    throw new IllegalArgumentException("Solo los participantes pueden registrar asistencia.");
                }
            }
        }

        return sesionesRepository.save(sesion);
    }

    @Override
    public List<SesionesModel> listarSesiones() {
        return sesionesRepository.findAll();
    }

    @Override
    public SesionesModel buscarSesionPorId(ObjectId id) {
        return sesionesRepository.findById(id).orElse(null);
    }

    @Override
    public SesionesModel actualizarSesion (ObjectId id, SesionesModel sesionActualizada) {
        SesionesModel sesionExistente = sesionesRepository.findById(id).orElse(null);

        if (sesionExistente == null) {
            throw new IllegalArgumentException("La sesion no existe.");
        }

        sesionActualizada.setId(id);

        return sesionesRepository.save(sesionActualizada);
    }
}
