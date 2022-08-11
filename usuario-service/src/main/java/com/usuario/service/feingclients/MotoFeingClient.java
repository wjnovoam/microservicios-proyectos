package com.usuario.service.feingclients;

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
@FeignClient(name = "moto-service", url = "http://localhost:8003", path="/moto")
public interface MotoFeingClient {
    @PostMapping
    Moto save(@RequestBody Moto moto);

    @GetMapping("/usuario/{usuarioId}")
    List<Moto> getMotos(@PathVariable("usuarioId") Long usuarioId);
}