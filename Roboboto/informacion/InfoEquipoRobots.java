/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.Roboboto.informacion;

import icaro.infraestructura.recursosOrganizacion.configuracion.ItfUsoConfiguracion;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author Valentina
 */
public class InfoEquipoRobots extends InfoTodosRobots{
    
    protected String idLider;
    
    public InfoEquipoRobots(ItfUsoConfiguracion itfConfig, ArrayList<String> teamRobotIds, String idLider) {
        super(itfConfig, teamRobotIds);
        this.idLider = idLider;
    }
     
    public InfoEquipoRobots(ItfUsoConfiguracion itfConfig, ArrayList<String> teamRobotIds, String grupoID, String idLider) {
        super(itfConfig,teamRobotIds,grupoID);
        this.idLider = idLider;
    }

    public String getIdLider() {
        return idLider;
    }

    public void setIdLider(String idLider) {
        this.idLider = idLider;
    }
    
    
    
}
