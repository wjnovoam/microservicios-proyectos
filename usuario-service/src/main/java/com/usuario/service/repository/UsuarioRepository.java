package com.usuario.service.repository;

import com.usuario.service.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author William Johan Novoa Melendrez
 * @date 11/08/2022
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}