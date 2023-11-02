/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author X1
 */
public class Encuesta {
    private int encuesta_id;
    private int user_id;
    private String nombre;
    private String sexo;
    private String deporte_favorito;
    private String nivel_estudio;
    private String temas_favoritos;
    private String fecha;
    private String hora;

    public Encuesta() {
    }

    public Encuesta(int user_id, String nombre, String sexo, String deporte_favorito, String nivel_estudio, String temas_favoritos, String fecha, String hora) {
        this.user_id = user_id;
        this.nombre = nombre;
        this.sexo = sexo;
        this.deporte_favorito = deporte_favorito;
        this.nivel_estudio = nivel_estudio;
        this.temas_favoritos = temas_favoritos;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Encuesta(int encuesta_id, int user_id, String nombre, String sexo, String deporte_favorito, String nivel_estudio, String temas_favoritos, String fecha, String hora) {
        this.encuesta_id = encuesta_id;
        this.user_id = user_id;
        this.nombre = nombre;
        this.sexo = sexo;
        this.deporte_favorito = deporte_favorito;
        this.nivel_estudio = nivel_estudio;
        this.temas_favoritos = temas_favoritos;
        this.fecha = fecha;
        this.hora = hora;
    }

    /**
     * @return the encuesta_id
     */
    public int getEncuesta_id() {
        return encuesta_id;
    }

    /**
     * @param encuesta_id the encuesta_id to set
     */
    public void setEncuesta_id(int encuesta_id) {
        this.encuesta_id = encuesta_id;
    }

    /**
     * @return the user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the deporte_favorito
     */
    public String getDeporte_favorito() {
        return deporte_favorito;
    }

    /**
     * @param deporte_favorito the deporte_favorito to set
     */
    public void setDeporte_favorito(String deporte_favorito) {
        this.deporte_favorito = deporte_favorito;
    }

    /**
     * @return the nivel_estudio
     */
    public String getNivel_estudio() {
        return nivel_estudio;
    }

    /**
     * @param nivel_estudio the nivel_estudio to set
     */
    public void setNivel_estudio(String nivel_estudio) {
        this.nivel_estudio = nivel_estudio;
    }

    /**
     * @return the temas_favoritos
     */
    public String getTemas_favoritos() {
        return temas_favoritos;
    }

    /**
     * @param temas_favoritos the temas_favoritos to set
     */
    public void setTemas_favoritos(String temas_favoritos) {
        this.temas_favoritos = temas_favoritos;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }
    
    
}
