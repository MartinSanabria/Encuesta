/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author X1
 */
public class Encuesta {
    private int ID;
    private int usuarioID;
    private String nombre;
    private String sexo;
    private String deporteFavorito;
    private String nivelEstudio;
    private String temasFavoritos;
    private String fecha;
    private String hora;

    public Encuesta() {
    }

    public Encuesta(int usuarioID, String nombre, String sexo, String deporteFavorito, String nivelEstudio, String temasFavoritos, String fecha, String hora) {
        this.usuarioID = usuarioID;
        this.nombre = nombre;
        this.sexo = sexo;
        this.deporteFavorito = deporteFavorito;
        this.nivelEstudio = nivelEstudio;
        this.temasFavoritos = temasFavoritos;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Encuesta(int ID, int usuarioID, String nombre, String sexo, String deporteFavorito, String nivelEstudio, String temasFavoritos, String fecha, String hora) {
        this.ID = ID;
        this.usuarioID = usuarioID;
        this.nombre = nombre;
        this.sexo = sexo;
        this.deporteFavorito = deporteFavorito;
        this.nivelEstudio = nivelEstudio;
        this.temasFavoritos = temasFavoritos;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDeporteFavorito() {
        return deporteFavorito;
    }

    public void setDeporteFavorito(String deporteFavorito) {
        this.deporteFavorito = deporteFavorito;
    }

    public String getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(String nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    public String getTemasFavoritos() {
        return temasFavoritos;
    }

    public void setTemasFavoritos(String temasFavoritos) {
        this.temasFavoritos = temasFavoritos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
