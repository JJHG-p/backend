package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.Model.ActividadesModel;
import backend.Repository.IActividadesRepository;

@Service
public class ActividadServiceImp implements IActividadService{
    @Autowired
    IActividadesRepository actividadesRepository;

    @Autowired
    IUsuarioService usuarioService;

    @Override
    public ActividadesModel crearActividad(ActividadesModel actividad) {
        ActividadesModel actividadRegistrada = actividadesRepository.save(actividad);
        System.out.println(actividadRegistrada);
        return actividadRegistrada;
    }

    @Override
    public List<ActividadesModel> listarActividades() {
        return actividadesRepository.findAll();
    }

    @Override
    public ActividadesModel buscarActividadPorId(ObjectId id) {
        return actividadesRepository.findById(id).orElse(null);
    }
}
