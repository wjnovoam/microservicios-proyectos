package com.usuario.service.controller;

import com.usuario.service.entity.Usuario;
import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;
import com.usuario.service.service.UsuarioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author William Johan Novoa Melendrez
 * @date 11/08/2022
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        List<Usuario> usuarios = usuarioService.getAll();

        if(usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") Long id){
        Usuario usuario = usuarioService.getUsuarioById(id);

        if (usuario == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        Usuario nuevoUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @CircuitBreaker(name = "carrosCB", fallbackMethod = "fallBackGetCarros")
    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarros(@PathVariable("usuarioId") Long id){
        Usuario usuario = usuarioService.getUsuarioById(id);

        if (usuario == null){
            return ResponseEntity.notFound().build();
        }

        List<Carro> carros = usuarioService.getCarros(id);

        return ResponseEntity.ok(carros);
    }

    @CircuitBreaker(name = "motosCB", fallbackMethod = "fallBackGetMotos")
    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotos(@PathVariable("usuarioId") Long id){
        Usuario usuario = usuarioService.getUsuarioById(id);

        if (usuario == null){
            return ResponseEntity.notFound().build();
        }

        List<Moto> motos = usuarioService.getMotos(id);

        return ResponseEntity.ok(motos);
    }

    @CircuitBreaker(name = "carrosCB", fallbackMethod = "fallBackSaveCarro")
    @PostMapping("carro/{usuarioId}")
    public ResponseEntity<Carro> guardarCarro(@PathVariable("usuarioId") Long usuarioId, @RequestBody Carro carro){
        Carro nuevoCarro = usuarioService.saveCarro(usuarioId, carro);
        return ResponseEntity.ok(nuevoCarro);
    }

    @CircuitBreaker(name = "motosCB", fallbackMethod = "fallBackSaveMoto")
    @PostMapping("moto/{usuarioId}")
    public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") Long usuarioId, @RequestBody Moto moto){
        Moto nuevaMoto = usuarioService.saveMoto(usuarioId, moto);
        return ResponseEntity.ok(nuevaMoto);
    }

    @CircuitBreaker(name = "todosCB", fallbackMethod = "fallBackGetTodos")
    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") Long usuarioId){
        Map<String, Object> resultado = usuarioService.getUsuarioAndVehiculos(usuarioId);
        return ResponseEntity.ok(resultado);
    }

    private ResponseEntity<List<Carro>> fallBackGetCarros(@PathVariable("usuarioId") Long id, RuntimeException exception){
        return new ResponseEntity("El usuario: "+id+" tiene los carros en el taller", HttpStatus.OK);
    }

    private ResponseEntity<List<Carro>> fallBackSaveCarro(@PathVariable("usuarioId") Long id, @RequestBody Carro carro,
                                                          RuntimeException exception){
        return new ResponseEntity("El usuario: "+id+" no tiene dinero para los carros", HttpStatus.OK);
    }

    private ResponseEntity<List<Moto>> fallBackGetMotos(@PathVariable("usuarioId") Long id, RuntimeException exception){
        return new ResponseEntity("El usuario: "+id+" tiene las motos en el taller", HttpStatus.OK);
    }

    private ResponseEntity<List<Moto>> fallBackSaveMoto(@PathVariable("usuarioId") Long id, @RequestBody Moto moto,
                                                          RuntimeException exception){
        return new ResponseEntity("El usuario: "+id+" no tiene dinero para las motos", HttpStatus.OK);
    }

    private ResponseEntity<List<Moto>> fallBackGetTodos(@PathVariable("usuarioId") Long id, @RequestBody Moto moto,
                                                        RuntimeException exception){
        return new ResponseEntity("El usuario: "+id+" tiene los vehiculos en el taller", HttpStatus.OK);
    }
}