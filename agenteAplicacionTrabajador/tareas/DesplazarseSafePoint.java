/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.agentes.agenteAplicacionTrabajador.tareas;

import icaro.aplicaciones.Rosace.informacion.Coordinate;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.InfoCompMovimiento;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.ItfUsoMovimientoCtrl;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.MisObjetivos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import org.apache.log4j.Logger;

/**
 *
 * @author Javier
 */
public class DesplazarseSafePoint extends TareaSincrona{
    
     private Logger log = Logger.getLogger(this.getClass().getSimpleName());
    
    @Override
   public void ejecutar(Object... params) {
       
       int velocidadCruceroPordefecto = 5;// metros por segundo
       
       MisObjetivos misObjs = (MisObjetivos) params[0];
        Focus focoActual = (Focus) params[1];
        InfoCompMovimiento infoComMov = (InfoCompMovimiento) params[3];
        String identAgente =this.getIdentAgente();
        String identTarea= this.getIdentTarea();
        Objetivo objConseguido = focoActual.getFoco();
        if (objConseguido!= null){
            objConseguido.setPriority(-1);
            misObjs.cambiarPrioridad(objConseguido);
        }
        Objetivo nuevoObj = misObjs.getobjetivoMasPrioritario();
        trazas.aceptaNuevaTrazaEjecReglas(identAgente, "Se ejecuta la tarea " + identTarea + 
                        "Objetivo conseguido :  " + objConseguido + "Nuevo objetivo a conseguir  :  " + nuevoObj
                        + " Los objetivos en la cola son  :  " + misObjs.getMisObjetivosPriorizados() + "\n");
                log.debug("Se ejecuta la tarea " + identTarea + 
                        "Objetivo conseguido :  " + objConseguido + "Nuevo objetivo a conseguir  :  " + nuevoObj
                        + " Los objetivos en la cola son  :  " + misObjs.getMisObjetivosPriorizados() + "\n"); 
        if (nuevoObj.getPriority()>0 ){
//            if (!objActual.getobjectReferenceId().equals(victima.getName()))// no se hace nada y se indica un error pq el objetivo debe ser el que esta como mas prioritario  
//            {
//                trazas.trazar(this.getIdentAgente(), "Se ejecuta la tarea " + this.getIdentTarea()
//                        + " El ident de la victima debe coindidir con el del objetivo mas prioritario :  " + victima + "\n", InfoTraza.NivelTraza.error);
//            } else {
//                 Objetivo    nuevoObj = misObjs.getobjetivoMasPrioritario();
//                 if (nuevoObj != null) {      
            ItfUsoMovimientoCtrl itfcompMov = (ItfUsoMovimientoCtrl) infoComMov.getitfAccesoComponente();
            itfcompMov.moverAdestino(nuevoObj.getobjectReferenceId(), new Coordinate(0,0,0), velocidadCruceroPordefecto); // se pondra la verlocidad por defecto 
            infoComMov.setitfAccesoComponente(itfcompMov);
                nuevoObj.setSolving();
                this.getEnvioHechos().actualizarHechoWithoutFireRules(infoComMov);
                this.getEnvioHechos().actualizarHechoWithoutFireRules(nuevoObj);
//                    trazas.aceptaNuevaTrazaEjecReglas(this.identAgente, "Se elimina el objetivo  " + objActual
//                            + " Los objetivos en la cola son  :  " + misObjs.getMisObjetivosPriorizados() + "\n");
                trazas.aceptaNuevaTrazaEjecReglas(identAgente, "Se ejecuta la tarea " + identTarea
                        + " Se inicia el movimiento." + "\n"+
                        "Objetivo conseguido :  " + objConseguido + "Nuevo objetivo a focalizar  :  " + nuevoObj
                        + " Los objetivos en la cola son  :  " + misObjs.getMisObjetivosPriorizados() + "\n");
               log.debug("Se ejecuta la tarea " + identTarea
                        + " Se inicia el movimiento. " + "\n"+
                        "Objetivo conseguido :  " + objConseguido + "Nuevo objetivo a focalizar  :  " + nuevoObj
                        + " Los objetivos en la cola son  :  " + misObjs.getMisObjetivosPriorizados() + "\n"); 
            } // no hay objetivos para salvar victimas el foco se pone a null
        else{

            trazas.aceptaNuevaTrazaEjecReglas(identAgente, "Se ejecuta la tarea " + identTarea
                        + "Nuevo objetivo a focalizar  :  " + nuevoObj + " Prioridad del objetivo : " + nuevoObj.getPriority()
                        + " Sin orden de movimiento por no tener victimas en la cola."
                        + " Los objetivos en la cola son  :  " + misObjs.getMisObjetivosPriorizados() + "\n");
             log.debug("\n" + identAgente + "Se ejecuta la tarea " + getIdentTarea() + " Movimiento realizado " + "\n\n");
             nuevoObj = null;
        }    
            focoActual.setFoco(nuevoObj);
            this.getEnvioHechos().actualizarHechoWithoutFireRules(misObjs);
            this.getEnvioHechos().actualizarHecho(focoActual);
       
   }
}
