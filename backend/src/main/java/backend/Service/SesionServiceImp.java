package backend.Service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.Model.SesionesModel;
import backend.Repository.ISesionesRepository;

@Service
public class SesionServiceImp implements ISesionService{
    @Autowired
    ISesionesRepository sesionesRepository;

    @Override
    public SesionesModel crearSesion(SesionesModel sesion) {
        SesionesModel sesionRegistrada = sesionesRepository.save(sesion);
        System.out.println(sesionRegistrada);
        return sesionRegistrada;
    }

    @Override
    public List<SesionesModel> listarSesiones() {
        return sesionesRepository.findAll();
    }

    @Override
    public SesionesModel buscarSesionPorId(ObjectId id) {
        return sesionesRepository.findById(id).orElse(null);
    }
}
