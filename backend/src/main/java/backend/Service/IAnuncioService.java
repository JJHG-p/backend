package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import backend.Model.AnunciosModel;

public interface IAnuncioService {
    AnunciosModel crearAnuncio(AnunciosModel anuncio);
    List<AnunciosModel> listarAnuncios();
    AnunciosModel buscarAnuncioPorId(ObjectId id);
    AnunciosModel actualizarAnuncio (ObjectId id, AnunciosModel anuncioActualizado);
}
