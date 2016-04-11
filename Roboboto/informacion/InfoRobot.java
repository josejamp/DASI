/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.Roboboto.informacion;

import icaro.aplicaciones.Rosace.informacion.Coordinate;
import icaro.aplicaciones.Rosace.informacion.RobotCapability;
import icaro.aplicaciones.agentes.componentesInternos.movimientoCtrl.InfoCompMovimiento;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Javier
 */
public class InfoRobot {
    
    protected String idRobot;

    protected String idRobotRol;

    protected int availableEnergy;
    
    protected Estado estado;

    protected Point robotCoordinateActualP;
    protected Point robotCoordinateAnteriorP ;
    protected Coordinate robotCoordinate;
    
    protected final double limiteDespalzamiento = 0.5;

    protected  List<RobotCapability> robotCapabilities ;

    protected RobotCapability capablity;
    
    protected InfoCompMovimiento infoCompMovt;

    public InfoRobot() {
        robotCoordinateAnteriorP = new Point(0,0);
                robotCoordinateActualP = new Point(1,1);
//                idRobot = robotId;
                idRobotRol = "indefinido";
                availableEnergy = 100000;
                robotCapabilities = new ArrayList<RobotCapability>();
//                robotCapabilities.add(robotCapability);
//                capablity = new RobotCapability();
//                if (robotCapabilities.size()==0)robotCapabilities.add(capablity );
//                robotCapabilities.add(20);
    }
   public  InfoRobot(ArrayList<RobotCapability> initialCapb) {
        robotCoordinateAnteriorP = new Point(0,0);
                robotCoordinateActualP = new Point(1,1);
//                idRobot = robotId;
                idRobotRol = "indefinido";
                availableEnergy = 100000;
                robotCapabilities = new ArrayList<RobotCapability>();
//                robotCapabilities.add("camera");
                robotCapabilities = initialCapb;
//                robotCapabilities.add("camara");
//                robotCapabilities.add(20);
    }  
       	
	public void setIdRobot(String id){
		this.idRobot = id;
	}
        @XmlElement (name = "idRobot")
	public String getIdRobot(){
		return this.idRobot;
	}
        public void setIdRobotRol(String id){
		this.idRobotRol = id;
	}
        @XmlElement (name = "idRobotRol")
	public String getIdRobotRol(){
		return this.idRobotRol;
	}		
	public void setAvailableEnergy(int energy){
		this.availableEnergy = energy;
	}
        @XmlElement (name = "availableEnergy")
	public int getAvailableEnergy(){
		return this.availableEnergy;
	}	
    public  void setRobotCoordinate(Coordinate coord){
       
        this.robotCoordinate = coord; 
                
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public  Coordinate getRobotCoordinate(){
        if(robotCoordinate==null)robotCoordinate = new Coordinate(robotCoordinateActualP.x, robotCoordinateActualP.y, 0);  
        return robotCoordinate;
    }
    @XmlElement (name = "robotCoordinateP")
    public Point getLocPoint() {
        return this.robotCoordinateActualP;
    }
    public void setLocPoint(Point punto) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         this.robotCoordinateActualP=punto;
         if ( this.robotCoordinate == null)robotCoordinate = new Coordinate(0,0,0);
         this.robotCoordinateActualP = punto;
         this.robotCoordinate.setX(punto.x);
         this.robotCoordinate.setY(punto.y);
         
    }
    public void setInfoCompMovt(InfoCompMovimiento compInfo){
        this.infoCompMovt = compInfo;    	
    }
    
    public InfoCompMovimiento getInfoCompMovt(){
    	return this.infoCompMovt;
    }

//    public void addRobotCapability(String capabilityId){
//    	boolean encontrado = false;
//        int numCapabilities = robotCapabilities.size();
//        if(numCapabilities >0 ){
//            int i=0; 
//            while(i<robotCapabilities.size()& !encontrado){
//                if(capabilityId.equals(robotCapabilities.get(i)) )encontrado =true ;
//                i++;    
//            }
//        }
//         if (!encontrado)this.robotCapabilities.add(capabilityId);
//       
//    }
    public void deleteRobotCapability(String capabilityId){
//    	int i=0; boolean encontrado = false;
//        while(i<robotCapabilities.size()& !encontrado){
//        if(capabilityId ==robotCapabilities.get(i) ){
//            encontrado =true ;
//            robotCapabilities.remove(i);
//        }
//        else i++;    
//        }
        if (robotCapabilities.contains(capabilityId))robotCapabilities.remove(capabilityId);
    }
    
    public void setRobotCapability(RobotCapability capabilityR){
           if(!robotCapabilities.contains(capabilityR)) robotCapabilities.add(capabilityR);
           
    }
    @XmlElement (name = "robotCapability")
    public RobotCapability getRobotCapability(String identCapab){
         int i=0; boolean encontrado = false;
        while(i<robotCapabilities.size()& !encontrado){
        if(robotCapabilities.get(i).getNombre().equalsIgnoreCase(identCapab) )
            encontrado =true ; 
        else i++;    
        }
           if (encontrado) return robotCapabilities.get(i);
           else return null;
    }
     @XmlElement (name = "robotCapabilities")   
    public List<RobotCapability> getRobotCapabilities(){
    	return this.robotCapabilities;
    }
    public boolean sinMovimientoSignificativo (){
        if (robotCoordinateAnteriorP == null) return false;
        return (limiteDespalzamiento>=Math.abs(robotCoordinateActualP.getY()-robotCoordinateAnteriorP.getY()) && 
                limiteDespalzamiento>=Math.abs(robotCoordinateActualP.getX()-robotCoordinateAnteriorP.getX()) );
    }
    @Override
    public Object clone(){
        Object obj=null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }
    
    @Override
    public String toString(){    	
    	return "Robot: id->" + this.getIdRobot() + 
                " ; Robot: Rol->" + this.getIdRobotRol() +
    			" ; engergylevel->" + this.getAvailableEnergy() + 
    			" ; coordinate->" + this.getRobotCoordinate();    	    	    	     	
    }
}
    
    
