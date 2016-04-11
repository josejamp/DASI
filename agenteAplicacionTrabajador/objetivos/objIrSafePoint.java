/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.agentes.agenteAplicacionTrabajador.objetivos;

import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;

/**
 *
 * @author Valentina
 * 
 * La informacion de dónde está el safe point debería poder ser accedida desde
 * la tarea correspondiente a través de un recurso.

 */
public class objIrSafePoint extends Objetivo{
    
    public objIrSafePoint(){
        super.setgoalId("IrSafePoint");
    }
    
}
