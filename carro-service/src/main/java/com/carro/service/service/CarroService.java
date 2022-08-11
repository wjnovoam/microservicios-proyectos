package com.carro.service.service;

import com.carro.service.entity.Carro;
import com.carro.service.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author William Johan Novoa Melendrez
 * @date 11/08/2022
 */
@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> getAll(){
        return carroRepository.findAll();
    }

    public Carro getCarroById(Long id){
        return carroRepository.findById(id).orElse(null);
    }

    public Carro save(Carro carro){
        return carroRepository.save(carro);
    }

    public List<Carro> byUsuarioId(Long usuarioId){
        return carroRepository.findByUsuarioId(usuarioId);
    }

}