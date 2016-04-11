/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.Roboboto.objetivosComunes;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;

/**
 *
 * @author Valentina
 */
public class objConfirmPeticionRemolque extends Objetivo{
    
    public String id; //id del robot remolcador que le va a remolcar
    
    public objConfirmPeticionRemolque(){
        super.setgoalId("ConfirmPeticionRemolque");
    }
    
    /**
     * 
     * @param id : id del robot remolcador que le va a remolcar
     */
     public objConfirmPeticionRemolque(String id){
        super.setgoalId("ConfirmPeticionRemolque");
        this.id = id;
    }
    
}
