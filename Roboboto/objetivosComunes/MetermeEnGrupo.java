/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.Roboboto.objetivosComunes;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;

/**
 *
 * @author Javier
 */
public class MetermeEnGrupo extends Objetivo {
	
    public String id;
    public String  misionId;
    
     public MetermeEnGrupo() {
        super.setgoalId("MetermeEnGrupo");
    }
     
    public MetermeEnGrupo(String misionId) {
         super.setgoalId("DefinirMiEquipo");
         this.misionId = misionId;
         super.setobjectReferenceId(misionId);
     }
     public void setmisionId(String misionId){
         this.misionId= misionId;
         super.setobjectReferenceId(misionId);
     }
     public String getmisionId(){
         return misionId;
     }
    
}
