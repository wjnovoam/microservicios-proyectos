package com.usuario.service.feingclients;

import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author William Johan Novoa Melendrez
 * @date 11/08/2022
 */
@FeignClient(name = "carro-service", url = "http://localhost:8002", path="/carro")
public interface CarroFeingClient {
    @PostMapping
    Carro save(@RequestBody Carro carro);

    @GetMapping("/usuario/{usuarioId}")
    List<Carro> getCarros(@PathVariable("usuarioId") Long usuarioId);
}