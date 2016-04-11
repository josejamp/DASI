/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.agentes.agenteAplicacionRescatador.tareas;

import icaro.aplicaciones.Roboboto.informacion.InfoRobotRescatador;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.MisObjetivos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

/**
 *
 * @author Valentina
 */
public class RecargarBateriaVictima extends TareaSincrona{
    
    @Override
   public void ejecutar(Object... params) {
        MisObjetivos misObjs = (MisObjetivos) params[0];
        Focus focoActual = (Focus) params[1];
        InfoRobotRescatador rescatador = (InfoRobotRescatador) params[3];
        InfoRobotRescatador victima = (InfoRobotRescatador) params[3];
        victima.setAvailableEnergy( victima.getAvailableEnergy() + (rescatador.getAvailableEnergy()/2) );
        rescatador.setAvailableEnergy(rescatador.getAvailableEnergy()/2);
        
        this.getEnvioHechos().actualizarHechoWithoutFireRules(misObjs);
        this.getEnvioHechos().actualizarHecho(focoActual);
   }
    
}
