package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.Model.AnunciosModel;
import backend.Model.UsuariosModel;
import backend.Repository.IAnunciosRepository;

@Service
public class AnuncioServiceImp implements IAnuncioService{
    
    @Autowired
    IAnunciosRepository anunciosRepository;

    @Autowired
    IUsuarioService usuarioService;

    @Autowired
    IActividadService actividadService;

    @Override
    public AnunciosModel crearAnuncio(AnunciosModel anuncio) {
        UsuariosModel creador = usuarioService.buscarUsuarioPorId(anuncio.getCreadorId());
        if (creador == null) {
            throw new IllegalArgumentException("El creador del anuncio no existe.");
        }

        String rolCreador = creador.getRol().name();
        if (!"Coordinador".equals(rolCreador) && !"Instructor".equals(rolCreador)) {
            throw new IllegalArgumentException("Solo coordinadores e instructores pueden publicar anuncios.");
        }

        if (anuncio.getActividadId() != null) {
            if (actividadService.obtenerActividadPorId(anuncio.getActividadId()) == null) {
                throw new IllegalArgumentException("La actividad asociada al anuncio no existe.");
            }
        }
        return anunciosRepository.save(anuncio);
    }

    @Override
    public List<AnunciosModel> listarAnuncios() {
        return anunciosRepository.findAll();
    }

    @Override
    public AnunciosModel buscarAnuncioPorId(ObjectId id) {
        return anunciosRepository.findById(id).orElse(null);
    }

    @Override
    public AnunciosModel actualizarAnuncio (ObjectId id, AnunciosModel anuncioActualizado) {
        AnunciosModel anuncioExistente = anunciosRepository.findById(id).orElse(null);

        if (anuncioExistente == null) {
            throw new IllegalArgumentException("El anuncio no existe.");
        }

        anuncioActualizado.setId(id);

        return anunciosRepository.save(anuncioActualizado);
    }
}
