/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import DataModel.DeliveryDataModel;
import DataModel.UserDataModel;
import Server.DeliverySessionBeanRemote;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Adne
 */
public class DeliveryManager 
{

    private DeliverySessionBeanRemote lookupDeliverySessionBeanRemote() 
    {
        try 
        {
            Context c = new InitialContext();
            //return (DeliverySessionBeanRemote) c.lookup("java:comp/env/DeliverySessionBean");
            return (DeliverySessionBeanRemote) c.lookup("java:global/slit4/slit4-ejb/DeliverySessionBean");
        } 
        catch (NamingException ne) 
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    public void addDelivery(DeliveryDataModel deliveryDataModel, UserDataModel userDataModel)
    {
        lookupDeliverySessionBeanRemote().addDelivery(deliveryDataModel, userDataModel);
    }
    
    public List<DeliveryDataModel> getAllDeliveries()
    {
        return lookupDeliverySessionBeanRemote().getDeliveryList();
    }
    
    public DeliveryDataModel getFirst()
    {
        List<DeliveryDataModel> deliveryDataModelList = this.getAllDeliveries();
        List<DeliveryDataModel> deliveryDataModelListSorted = new ArrayList<DeliveryDataModel>();
        Map<Integer, DeliveryDataModel> deliveryMap = new TreeMap<Integer, DeliveryDataModel>();
        
        for(DeliveryDataModel ddm: deliveryDataModelList)
        {
            deliveryMap.put(ddm.getDeliveryId(), ddm);
        }
        
        for(Integer key: deliveryMap.keySet())
        {
            deliveryDataModelListSorted.add(deliveryMap.get(key));
        }
        
        return deliveryDataModelListSorted.get(0);
    }
    
    public ArrayList<DeliveryDataModel> getByNameSearch(String searchWord)
    {
        List<DeliveryDataModel> deliveryDataModelList = this.getAllDeliveries();       
        ArrayList<DeliveryDataModel> deliveryDataModelListSearched = new ArrayList<DeliveryDataModel>();
        
        for(DeliveryDataModel deliveryDataModel: deliveryDataModelList)
        {
            String fullName = deliveryDataModel.getUserDataModel().getFirstName() + " " + deliveryDataModel.getUserDataModel().getLastName();
            
            if(fullName.toLowerCase().contains((CharSequence) searchWord.toLowerCase()))
            {
                boolean isInList = false;
                        
                for(DeliveryDataModel ddm: deliveryDataModelListSearched)
                {
                    if(ddm.getUserDataModel().getUsersId() == deliveryDataModel.getDeliveryId())
                    {
                        isInList = true;
                    }
                }
                
                if(isInList == false)
                {
                    if(deliveryDataModel.getStatus() == 0)
                    {
                        deliveryDataModelListSearched.add(deliveryDataModel);
                    }
                }
            }
        }
        
        return deliveryDataModelListSearched;
    }
    
    public void changeStatus(DeliveryDataModel deliveryDataModel, int status)
    {
        lookupDeliverySessionBeanRemote().changeStatus(deliveryDataModel, status);
    }
    
    public void addReviewComment(DeliveryDataModel deliveryDataModel, String comment)
    {
        lookupDeliverySessionBeanRemote().addReviewComment(deliveryDataModel, comment);
    }
    
    public List<DeliveryDataModel> getDeliveriesByName(UserDataModel user)
    {
        List<DeliveryDataModel> allDeliveries = lookupDeliverySessionBeanRemote().getDeliveryList();
        List<DeliveryDataModel> userDeliveries = new ArrayList<DeliveryDataModel>();
        
        for(DeliveryDataModel d: allDeliveries)
        {
            if(Objects.equals(d.getUserDataModel().getUsersId(), user.getUsersId()))
            {
                userDeliveries.add(d);
            }
        }
        
        return userDeliveries;
    }
}
