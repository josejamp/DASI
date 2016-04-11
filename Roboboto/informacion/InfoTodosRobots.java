/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.aplicaciones.Roboboto.informacion;

import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.recursosOrganizacion.configuracion.ItfUsoConfiguracion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Javier
 */
public class InfoTodosRobots {
    
    protected ItfUsoConfiguracion itfConfig  ;
    protected ArrayList<String> teamRobotIds;
    protected boolean inicioContactoConEquipo = false;
    protected String identGrupo;

    protected Map<String, InfoRobot> teamInfoAgentStatus;
    
    public InfoTodosRobots(String idGrupo){
        this.identGrupo = idGrupo;
        this.teamRobotIds = new ArrayList<String>();
        
        this.teamInfoAgentStatus = new HashMap<String,InfoRobot>();
           for(int i = 0; i < teamRobotIds.size();  i++ ) {
               String aux = (String) teamRobotIds.get(i); 
               this.teamInfoAgentStatus.put(aux, null);
            }
    }
    
    public InfoTodosRobots(ItfUsoConfiguracion itfConfig, ArrayList<String> teamRobotIds) {
        this.itfConfig = itfConfig;
        this.teamRobotIds = teamRobotIds;
        
        teamInfoAgentStatus = new HashMap<String,InfoRobot>();
           for(int i = 0; i < teamRobotIds.size();  i++ ) {
               String aux = (String) teamRobotIds.get(i); 
               teamInfoAgentStatus.put(aux, null);
            }
    }
    
     public InfoTodosRobots(ItfUsoConfiguracion itfConfig, ArrayList<String> teamRobotIds, String grupoID) {
        this.itfConfig = itfConfig;
        this.teamRobotIds = teamRobotIds;
        this.identGrupo = grupoID;
        
        teamInfoAgentStatus = new HashMap<String,InfoRobot>();
           for(int i = 0; i < teamRobotIds.size();  i++ ) {
               String aux = (String) teamRobotIds.get(i); 
               teamInfoAgentStatus.put(aux, null);
            }
    }

    public ItfUsoConfiguracion getItfConfig() {
        return itfConfig;
    }

    public ArrayList<String> getTeamRobotIds() {
        return teamRobotIds;
    }

    public void setItfConfig(ItfUsoConfiguracion itfConfig) {
        this.itfConfig = itfConfig;
    }

    public void setTeamRobotIds(ArrayList<String> teamRobotIds) {
        this.teamRobotIds = teamRobotIds;
    }

    public boolean isInicioContactoConEquipo() {
        return inicioContactoConEquipo;
    }

    public void setInicioContactoConEquipo(boolean inicioContactoConEquipo) {
        this.inicioContactoConEquipo = inicioContactoConEquipo;
    }

    public String getIdentGrupo() {
        return identGrupo;
    }

    public void setIdentGrupo(String identGrupo) {
        this.identGrupo = identGrupo;
    }
    
    
    public boolean addMember(String robotID){
        if(this.teamRobotIds.contains(robotID)){ 
            this.teamRobotIds.add(robotID);
            return true;
        }
        else return false;
    }
    
    public boolean deleteMember(String robotID){
        if(this.teamRobotIds.contains(robotID)){ 
            this.teamRobotIds.remove(robotID);
            return true;
        }
        else return false;
    }
    
    public boolean isMember(String robotID){
        return this.teamRobotIds.contains(robotID);
    }
    
    public int numMembers() {
        return this.teamRobotIds.size();
    }
    
    private ArrayList<String> getIdentsAgentesAplicacionRegistrados(){
       
         try {   
            itfConfig = (ItfUsoConfiguracion) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfaz(NombresPredefinidos.ITF_USO+"Configuracion");
  //        teamRobotIds = itfConfig.getIdentificadoresInstanciasAgentesAplicacion();
            return itfConfig.getIdentificadoresInstanciasAgentesAplicacion(); 
            } catch (Exception ex) {
            //Logger.getLogger(InfoTodosRobots.class.getName()).log(Level.SEVERE, null, ex);
            return null;
            }  
    }
    
    public synchronized ArrayList<String> getTeamMemberIDsWithThisRol(String rolId){
         ArrayList<String> agtesConMismoRol = new ArrayList();
       //  int indiceagtesConMirol=0;
         InfoRobot estatusAgte;
         String identAgte;
         for (int i = 0; i < teamRobotIds.size();  i++ ){
            identAgte = teamRobotIds.get(i);
            estatusAgte =teamInfoAgentStatus.get(identAgte);
            if(estatusAgte != null)
                if(estatusAgte.getIdRobotRol().equals(rolId)){
                    agtesConMismoRol.add(teamRobotIds.get(i));                      
                }
         }
         return agtesConMismoRol;
    }
    
    public synchronized InfoRobot getTeamMemberStatus(String identMember){ 
         return teamInfoAgentStatus.get(identMember);
     }
    
    public synchronized String getTeamMemberRol(String identAgte){ 
        return teamInfoAgentStatus.get(identAgte).getIdRobotRol();
     }
     public synchronized void setTeamMemberStatus(String identMember, InfoRobot robotSt){ 
         teamInfoAgentStatus.put(identMember, robotSt);
     }
     public synchronized Boolean isRobotStatusDefined(String robtId){ 
        return  teamInfoAgentStatus.containsKey(robtId);
     }
}
