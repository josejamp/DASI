/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.Roboboto.tareasComunes;

import icaro.aplicaciones.Roboboto.informacion.InfoRobot;
import icaro.aplicaciones.Rosace.informacion.Coordinate;
import icaro.aplicaciones.Rosace.informacion.VocabularioRosace;
import icaro.aplicaciones.Rosace.utils.AccesoPropiedadesGlobalesRosace;
import icaro.aplicaciones.Rosace.utils.ReadXMLTestRobots;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Javier
 */
public class InicializarInfoRobot extends TareaSincrona{
    
    	String valueid;
	
    @Override
   public void ejecutar(Object... params) {
    	 try {
        String miIdentAgte = this.getIdentAgente();
        
        //Lectura del fichero de robots. Aprovechamos para tener en memoria la configuracion de robots.
             
        String rutaFicheroRobotsTest = AccesoPropiedadesGlobalesRosace.getRutaFicheroRobotsTest();
           
    	ReadXMLTestRobots rXMLTRobots = new ReadXMLTestRobots(rutaFicheroRobotsTest);
        
		Document doc = rXMLTRobots.getDocument(rXMLTRobots.gettestFilePaht());
		//Obtain all the robots
		NodeList nodeLst = rXMLTRobots.getRobotsXMLStructure(doc, "robot");	
		int numeroRobotsSimulation = rXMLTRobots.getNumberOfRobots(nodeLst);	
		int j=0;
                boolean encontrado= false;     
                Element info = null ;  // Lo siguiente habria que quitarlo cuando se defina el recurso
      //         String identAgteSinIdentEquipo = miIdentAgte.replaceFirst(VocabularioRosace.IdentEquipoJerarquicoConCambioRol, "");
                while(j<numeroRobotsSimulation && !encontrado ){
  		    //Obtain info about robot located at the test        	
                    info = rXMLTRobots.getRobotElement(nodeLst, j);			        	
                     valueid = rXMLTRobots.getRobotIDValue(info, "id").replaceFirst(VocabularioRosace.IdentEquipoJerarquico, "");
      //               valueid= valueid.replaceFirst(VocabularioRosace.IdentEquipoJerarquicoConCambioRol, "");
                    if (identAgente.equals(valueid)){        		
                        encontrado = true; //Salir del bucle for. Se ha encontrado la informacion xml asociada al robot/agente que ejecuta esta tarea
                    } j++;
                }
                
            if (encontrado){     
                        int energy = rXMLTRobots.getRobotInitialEnergy(info, "initialenergy");
                        Coordinate initialCoordinate = rXMLTRobots.getRobotCoordinate(info);
                        float healRange = rXMLTRobots.getRobotHealRange(info, "healrange");	        	           	   
                        InfoRobot robotStatus = new InfoRobot();        	           	   
                            robotStatus.setIdRobot(valueid);
                            robotStatus.setAvailableEnergy(energy);        	   
                            robotStatus.setRobotCoordinate(initialCoordinate);
                            this.getEnvioHechos().insertarHecho(robotStatus);
        	} else this.trazas.trazar(miIdentAgte, "No se ha encontrado el fichero de inicializacion de Estatus", InfoTraza.NivelTraza.error);       	        	
        }
        catch (Exception e) {
	e.printStackTrace();
       }
   }
    
    
}
