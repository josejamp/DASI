/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.Roboboto.informacion;

import icaro.aplicaciones.Rosace.informacion.RobotCapability;
import java.util.ArrayList;

/**
 *
 * @author Javier
 */
public class InfoRobotRescatador extends InfoRobot{
    
    protected String idEquipo;
    
    protected String idLider;
    
    public InfoRobotRescatador() {
        super();
    }
    
    public InfoRobotRescatador(String idEquipo, String idLider) {
        super();
        this.idEquipo = idEquipo;
        this.idLider = idLider;
    }
    
    public InfoRobotRescatador(ArrayList<RobotCapability> initialCapb) {
        super(initialCapb);
    }
    
    public InfoRobotRescatador(ArrayList<RobotCapability> initialCapb, String idEquipo, String idLider) {
        super(initialCapb);
        this.idEquipo = idEquipo;
        this.idLider = idLider;
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public String getIdLider() {
        return idLider;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public void setIdLider(String idLider) {
        this.idLider = idLider;
    }
    
    
    
}
