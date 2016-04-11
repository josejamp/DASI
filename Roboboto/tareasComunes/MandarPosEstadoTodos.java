/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.Roboboto.tareasComunes;

import icaro.aplicaciones.Roboboto.informacion.InfoRobot;
import icaro.aplicaciones.Roboboto.informacion.MensajeEstado;
import java.util.ArrayList;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

/**
 *
 * @author Javier
 */
public class MandarPosEstadoTodos extends TareaSincrona{
    
     @Override
   public void ejecutar(Object... params) {
       try {
//               trazas = NombresPredefinidos.RECURSO_TRAZAS_OBJ;
               Objetivo objetivoEjecutantedeTarea = (Objetivo)params[0];
               ArrayList<String>  robots = (ArrayList<String>)params[1];
               InfoRobot info = (InfoRobot)params[2];
               String nombreAgenteEmisor = this.getIdentAgente();
                         //             String identTareaLong = getClass().getName();
               String identTarea = this.getIdentTarea();
               MensajeEstado msg_estado = new MensajeEstado(info.getIdRobot(), info.getEstado(), info.getRobotCoordinate());

               trazas.aceptaNuevaTraza(new InfoTraza(nombreAgenteEmisor, "Se Ejecuta la Tarea :"+ identTarea , InfoTraza.NivelTraza.debug));
               trazas.aceptaNuevaTraza(new InfoTraza("OrdenAsignacion",
                                                   "El robot " + this.identAgente + " manda el estado a los demas",
                                                   InfoTraza.NivelTraza.debug));
               this.getComunicator().informaraGrupoAgentes(msg_estado, robots);
              this.generarInformeOK(identTarea, objetivoEjecutantedeTarea, nombreAgenteEmisor, "DecisionDeIrEnviadaAtodos");
                  } catch (Exception e) {
                           e.printStackTrace();
        }
   }
    
}
