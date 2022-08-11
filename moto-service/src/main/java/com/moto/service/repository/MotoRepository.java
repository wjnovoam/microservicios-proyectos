package com.moto.service.repository;

import com.moto.service.entity.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author William Johan Novoa Melendrez
 * @date 11/08/2022
 */
@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {
    List<Moto> findByUsuarioId(Long usuarioId);

}