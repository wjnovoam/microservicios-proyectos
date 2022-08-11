package com.carro.service.repository;

import com.carro.service.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author William Johan Novoa Melendrez
 * @date 11/08/2022
 */
@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {
    List<Carro> findByUsuarioId(Long usuarioId);

}