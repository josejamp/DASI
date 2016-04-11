/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.Roboboto.tareasComunes;

import icaro.aplicaciones.Roboboto.informacion.InfoRobot;
import icaro.aplicaciones.Roboboto.informacion.InfoTodosRobots;
import icaro.aplicaciones.Rosace.informacion.InfoRolAgente;
import icaro.aplicaciones.Rosace.informacion.VocabularioRosace;
import icaro.infraestructura.entidadesBasicas.interfaces.InterfazUsoAgente;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import java.util.ArrayList;

/**
 *
 * @author Javier
 */
public class ContactarTodosRobots extends TareaSincrona{
    
   private InterfazUsoAgente agenteReceptor;
   private ArrayList <String> agentes;//resto de agentes                                
   private int mi_eval, mi_eval_nueva;
   private String nombreAgenteEmisor;

    @Override
    public void ejecutar(Object... params) {
            try {     
         InfoRobot miStatus = (InfoRobot)params[0];    
         InfoTodosRobots todosInfo = (InfoTodosRobots)params[1];  
          nombreAgenteEmisor = this.getAgente().getIdentAgente();
          agentes = todosInfo.getTeamRobotIds();
          trazas.aceptaNuevaTraza(new InfoTraza(nombreAgenteEmisor, "Se Ejecuta la Tarea :"+ identTarea , InfoTraza.NivelTraza.debug));
                    //            trazas.aceptaNuevaTraza(new InfoTraza(nombreAgenteEmisor, "Enviamos la evaluacion " + miEvaluacion, InfoTraza.NivelTraza.masterIA));          
          InfoRolAgente mirol = new InfoRolAgente(nombreAgenteEmisor,todosInfo.getIdentGrupo(),miStatus.getIdRobotRol(),VocabularioRosace.IdentMisionEquipo);
          trazas.aceptaNuevaTraza(new InfoTraza(nombreAgenteEmisor, "Enviamos informacion de Rol " + mirol.toString(), InfoTraza.NivelTraza.info));  
          this.getComunicator().informaraGrupoAgentes(mirol, agentes);
          todosInfo.setInicioContactoConEquipo(true);
          this.getEnvioHechos().actualizarHechoWithoutFireRules(todosInfo);
          trazas.aceptaNuevaTraza(new InfoTraza(nombreAgenteEmisor, "Numero de agentes de los que espero respuesta:" + agentes.size(), InfoTraza.NivelTraza.info));     
          this.generarInformeTemporizadoFromConfigProperty(VocabularioRosace.IdentTareaTimeOutContactarMiembrosEquipo,null,nombreAgenteEmisor, null);
          // en le caso de que ya la haya enviado la evaluacion no hago nada
            } catch (Exception e) {
                    e.printStackTrace();
            }
    }
    
}
