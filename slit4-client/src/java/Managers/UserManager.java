/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import DataModel.DeliveryDataModel;
import DataModel.UserDataModel;
import Server.UserSessionBeanRemote;
import java.util.ArrayList;
import java.util.HashMap;
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
public class UserManager 
{

    private UserSessionBeanRemote lookupUserSessionBeanRemote() 
    {
        try 
        {
            Context c = new InitialContext();
            //return (UserSessionBeanRemote) c.lookup("java:comp/env/UserSessionBean");
            return (UserSessionBeanRemote) c.lookup("java:global/slit4/slit4-ejb/UserSessionBean");
        } 
        catch (NamingException ne) 
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    public List<UserDataModel> getUsersList()
    {
        return lookupUserSessionBeanRemote().getAllUsers();
    }
    
    public List<UserDataModel> getUsersListSortedByDeliveries()
    {
        List<UserDataModel> userDataModelList = this.getUsersList();
        List<UserDataModel> userDataModelListSorted = new ArrayList<UserDataModel>();
        HashMap<UserDataModel, Integer> userDeliveryHash = new HashMap<UserDataModel, Integer>();
        
        for(UserDataModel udm: userDataModelList)
        {
            int amount = 0;
            
            for(DeliveryDataModel ddm: udm.getDeliveryList())
            {
                if(ddm.getStatus() == 1)
                {
                    amount++;
                }
            }
            userDeliveryHash.put(udm, amount);
        }
        
        for(UserDataModel key: userDeliveryHash.keySet())
        {
            userDataModelListSorted.add(key);
        }
        
        UserDataModel temp = new UserDataModel();
        
        for(int i = 0; i < userDataModelListSorted.size()-1; i++)
        {
            for(int j = 1; j < userDataModelListSorted.size()-i; j++)
            {
                if(userDeliveryHash.get(userDataModelListSorted.get(j-1)) < userDeliveryHash.get(userDataModelListSorted.get(j)))
                {
                    temp = userDataModelListSorted.get(j-1);
                    userDataModelListSorted.set(j-1, userDataModelListSorted.get(j));
                    userDataModelListSorted.set(j, temp);
                }
            }
        }
        
        return userDataModelListSorted;
    }
    
    public List<UserDataModel> getUsersByNameSearch(String searchWord)
    {
        List<UserDataModel> userList = this.getUsersList();
        List<UserDataModel> userListSearched = new ArrayList<UserDataModel>();
        
        for(UserDataModel udm: userList)
        {
            String fullName = udm.getFirstName() + " " + udm.getLastName();
            
            if(fullName.toLowerCase().contains(searchWord.toLowerCase()))
            {
                userListSearched.add(udm);
            }
        }
        
        return userListSearched;
    }
}
