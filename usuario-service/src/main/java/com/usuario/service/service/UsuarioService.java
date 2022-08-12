package com.usuario.service.service;

import com.usuario.service.entity.Usuario;
import com.usuario.service.feingclients.CarroFeingClient;
import com.usuario.service.feingclients.MotoFeingClient;
import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;
import com.usuario.service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author William Johan Novoa Melendrez
 * @date 11/08/2022
 */
@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarroFeingClient carroFeingClient;

    @Autowired
    private MotoFeingClient motoFeingClient;

    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Long id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Carro> getCarros(Long usuarioId){
        return restTemplate.getForObject("http://carro-service/carro/usuario/"+ usuarioId, List.class);
    }

    public List<Moto> getMotos(Long usuarioId){
        return restTemplate.getForObject("http://moto-service/moto/usuario/"+ usuarioId, List.class);
    }

    public Carro saveCarro(Long usuarioId, Carro carro){
        carro.setUsuarioId(usuarioId);

        return carroFeingClient.save(carro);
    }

    public Moto saveMoto(Long usuarioId, Moto moto){
        moto.setUsuarioId(usuarioId);

        return motoFeingClient.save(moto);
    }

    public Map<String, Object> getUsuarioAndVehiculos(Long usuarioId){
        Map<String, Object> resultado = new HashMap<>();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if(usuario == null){
            resultado.put("Mensaje", "El usuario no existe");
            return resultado;
        }

        resultado.put("Usuario", usuario);
        List<Carro> carros = carroFeingClient.getCarros(usuarioId);
        if(carros == null){
            resultado.put("Carros", "El usuario no tiene carros");
        }else {
            resultado.put("Carros", carros);
        }

        List<Moto> motos = motoFeingClient.getMotos(usuarioId);
        if(motos == null){
            resultado.put("Motos", "El usuario no tiene motos");
        }else {
            resultado.put("Motos", motos);
        }

        return resultado;
    }
}