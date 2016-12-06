/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import DataModel.ModulePlanDataModel;
import DataModel.SemesterPlanDataModel;
import DataModel.UserDataModel;
import Server.SemesterPlanSessionBeanRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Adne
 */
public class SemesterPlanManager 
{
    private SemesterPlanSessionBeanRemote lookupSemesterPlanSessionBeanRemote() 
    {
        try 
        {
            Context c = new InitialContext();
            //return (SemesterPlanSessionBeanRemote) c.lookup("java:comp/env/SemesterPlanSessionBean");
            return (SemesterPlanSessionBeanRemote) c.lookup("java:global/slit4/slit4-ejb/SemesterPlanSessionBean");
        } 
        catch (NamingException ne) 
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    public void addModulePlan(ModulePlanDataModel modulPlanDataModel, UserDataModel userDataModel)
    {
        lookupSemesterPlanSessionBeanRemote().addModulePlan(modulPlanDataModel, userDataModel);
    }
    
    public SemesterPlanDataModel getSemesterPlanById(UserDataModel user)
    {
        return lookupSemesterPlanSessionBeanRemote().getSemesterPlanById(user);
    }
}
