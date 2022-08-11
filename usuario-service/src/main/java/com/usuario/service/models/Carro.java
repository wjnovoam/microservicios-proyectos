package com.usuario.service.models;

/**
 * @author William Johan Novoa Melendrez
 * @date 11/08/2022
 */
public class Carro {

    private String marca;
    private String modelo;
    private Long usuarioId;

    public Carro() {
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}