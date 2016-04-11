/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.Roboboto.tareasComunes;

import icaro.aplicaciones.agentes.componentesInternos.InfoCompInterno;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.FactoriaAbstrCompInterno;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.ItfUsoMovimientoCtrl;
import icaro.infraestructura.entidadesBasicas.excepciones.ExcepcionEnComponente;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaAsincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Javier
 */
public class CrearComponentes extends TareaAsincrona {

    /** Creates a new instance of GenerarInfoTerminacion */
  

    @Override
    public void ejecutar(Object... params) {
        try {
            //        ItfUsoMovimientoCtrl ItfCom = FactoriaAbstrCompInterno.instance.crearComponenteInterno(null, this.getEnvioHechos());
                  InfoCompInterno infoCompMov = FactoriaAbstrCompInterno.instance().crearComponenteInterno("CompMovimiento", itfProcObjetivos);
                   ItfUsoMovimientoCtrl  itfcomp =  (ItfUsoMovimientoCtrl)  infoCompMov.getitfAccesoComponente();
                  itfcomp.inicializarInfoMovimiento(null, Integer.SIZE);
                           this.itfProcObjetivos.insertarHecho(infoCompMov);
            //             itfcomp.moverAdestino(null, Integer.MIN_VALUE);
                   this.trazas.trazar(this.identAgente, "se ejecuta la tarea : "+ this.identTarea, InfoTraza.NivelTraza.debug);
                //   this.getEnvioHechos().insertarHecho(cre);
        } catch (ExcepcionEnComponente ex) {
            Logger.getLogger(CrearComponentes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
