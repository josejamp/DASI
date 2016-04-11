/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.Roboboto.informacion;

import icaro.aplicaciones.Rosace.informacion.Coordinate;
import java.io.Serializable;

/**
 *
 * @author Javier
 */
public class MensajeEstado implements Serializable {
    
    public String identEmisor;
    public Estado estado;
    public Coordinate posicion;

    public MensajeEstado( ) {
        
    }
    
    public MensajeEstado(String identCCEmisor) {
        identEmisor= identCCEmisor;
        estado = Estado.OK;
        posicion = null;

    }
    public MensajeEstado(String identCCEmisor, Estado estado, Coordinate pos) {
        identEmisor= identCCEmisor;
        this.estado = estado;
        this.posicion = pos;

    }

    public String getIdentEmisor() {
        return identEmisor;
    }

    public Estado getEstado() {
        return estado;
    }

    public Coordinate getPosicion() {
        return posicion;
    }

    public void setIdentEmisor(String identEmisor) {
        this.identEmisor = identEmisor;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setPosicion(Coordinate posicion) {
        this.posicion = posicion;
    }
    
    
     @Override
        public String toString(){
            return "Agente Emisor :"+identEmisor + " . Estado: " + estado + " . Posicion: " + posicion + "\n ";
       }
    
    
}
