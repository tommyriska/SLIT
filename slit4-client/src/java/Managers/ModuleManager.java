/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import DataModel.ModuleDataModel;
import Server.ModuleSessionBeanRemote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Adne
 */
public class ModuleManager 
{

    private ModuleSessionBeanRemote lookupModuleSessionBeanRemote() 
    {
        try 
        {
            Context c = new InitialContext();
            //return (ModuleSessionBeanRemote) c.lookup("java:comp/env/ModuleSessionBean");
            return (ModuleSessionBeanRemote) c.lookup("java:global/slit4/slit4-ejb/ModuleSessionBean");
        } 
        catch (NamingException ne) 
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    public List<ModuleDataModel> getModulesList()
    {
        return lookupModuleSessionBeanRemote().getAllModules();
    }
    
    public void addModule(ModuleDataModel moduleDataModel)
    {
        lookupModuleSessionBeanRemote().addModule(moduleDataModel);
    }
}
