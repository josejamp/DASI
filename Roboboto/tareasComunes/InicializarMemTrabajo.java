/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.Roboboto.tareasComunes;

import icaro.aplicaciones.Roboboto.informacion.InfoRobot;
import icaro.aplicaciones.Roboboto.informacion.InfoTodosRobots;
import icaro.aplicaciones.Rosace.informacion.Coordinate;
import icaro.aplicaciones.Rosace.informacion.VictimsToRescue;
import icaro.aplicaciones.Rosace.utils.AccesoPropiedadesGlobalesRosace;
import icaro.aplicaciones.Rosace.utils.ReadXMLTestRobots;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Focus;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.MisObjetivos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Javier
 */
public class InicializarMemTrabajo extends TareaSincrona {
           String miIdentAgte ;
            String identEquipo;
   @Override
   public void ejecutar(Object... params) {
	   try {
             String identRolAgte = (String)params[0];
             miIdentAgte= this.getIdentAgente();
             this.getItfConfigMotorDeReglas().setDepuracionActivationRulesDebugging(false);
             this.getItfConfigMotorDeReglas().setDepuracionHechosInsertados(false);
             this.getItfConfigMotorDeReglas().setDepuracionHechosModificados(false);
             this.getItfConfigMotorDeReglas().setfactHandlesMonitoring_afterActivationFired_DEBUGGING(false);
             identEquipo = this.getItfUsoConfiguracion().getValorPropiedadGlobal(NombresPredefinidos.NOMBRE_PROPIEDAD_GLOBAL_IDENT_EQUIPO);
             this.getEnvioHechos().insertarHechoWithoutFireRules(new Focus());
             this.getEnvioHechos().insertarHechoWithoutFireRules(new MisObjetivos());
             this.getEnvioHechos().insertarHechoWithoutFireRules(new VictimsToRescue());
             InfoRobot miStatus = getRobotStatusInicial ( identRolAgte);        
                if (  miStatus != null){
                    InfoTodosRobots miEquipo = new InfoTodosRobots(identEquipo);
                    miEquipo.setTeamMemberStatus(miIdentAgte, miStatus); 
                    this.getEnvioHechos().insertarHechoWithoutFireRules(miStatus);
                    this.getEnvioHechos().insertarHecho(miEquipo);
                    }
                   else this.trazas.trazar(miIdentAgte, "No se ha encontrado el fichero de inicializacion de Estatus", InfoTraza.NivelTraza.error);
                      
       } catch (Exception e) {
	e.printStackTrace();
       }                
   }
  
private InfoRobot getRobotStatusInicial ( String identRol){
// Esto habria que cambiarlo cuando se defina el Recurso         
    String rutaFicheroRobotsTest = AccesoPropiedadesGlobalesRosace.getRutaFicheroRobotsTest();        
    	//ReadXMLTestRobots rXMLTRobots = new ReadXMLTestRobots(Constantes.rutasFicheroRobotsJerarquico);
    	ReadXMLTestRobots rXMLTRobots = new ReadXMLTestRobots(rutaFicheroRobotsTest);    	
		Document doc = rXMLTRobots.getDocument(rXMLTRobots.gettestFilePaht());
		//Obtain all the robots
		NodeList nodeLst = rXMLTRobots.getRobotsXMLStructure(doc, "robot");	
		int numeroRobotsSimulation = rXMLTRobots.getNumberOfRobots(nodeLst);	
		int j=0;
                boolean encontrado= false;
                Element info = null ;  // Lo siguienta habria que quitarlo cuando se defina el recurso
       //        String identAgteSinIdentEquipo = miIdentAgte.replaceFirst(identEquipo, "");
                while(j<numeroRobotsSimulation && !encontrado ){
  		    //Obtain info about robot located at the test        	
                    info = rXMLTRobots.getRobotElement(nodeLst, j);			        	
                    String valueid = rXMLTRobots.getRobotIDValue(info, "id");
                    if (miIdentAgte.equals(valueid)){        		
                        encontrado = true; //Salir del bucle for. Se ha encontrado la informacion xml asociada al robot/agente que ejecuta esta tarea
                    } j++;
                }
            if (encontrado){
                        int energy = rXMLTRobots.getRobotInitialEnergy(info, "initialenergy");
                        Coordinate initialCoordinate = rXMLTRobots.getRobotCoordinate(info);
                        InfoRobot robotStatus = new InfoRobot();        	           	   
                        robotStatus.setIdRobot(miIdentAgte);
                        robotStatus.setIdRobotRol(identRol);
                        robotStatus.setAvailableEnergy(energy);        	   
                        robotStatus.setRobotCoordinate(initialCoordinate);
                return robotStatus ;
            }
            else{
                trazas.trazar(miIdentAgte, "No se ha podido encontrar el identificador del agente en el fichero :"
                        +rutaFicheroRobotsTest , InfoTraza.NivelTraza.error);
                return null;
            }
    }
    
}
