/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DIV.Converter;
import DataModel.BlogDataModel;
import DataModel.BlogPostDataModel;
import DataModel.DeliveryDataModel;
import DataModel.FileDataModel;
import DataModel.ModulePlanDataModel;
import DataModel.SemesterPlanDataModel;
import DataModel.UserDataModel;
import Database.BlogPost;
import Database.Delivery;
import Database.DeliveryFile;
import Database.ModulePlan;
import Database.Users;
import Server.UserSessionBeanRemote;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Adne
 */
@Stateless
public class UserSessionBean implements UserSessionBeanRemote 
{
    @PersistenceContext(unitName="slit4-ejbPU")
    private EntityManager em;

    @Override
    public List<UserDataModel> getAllUsers() 
    {
        Converter converter = new Converter();
        List<UserDataModel> userModels = new ArrayList<UserDataModel>();
        
        try
        {
            Query query = em.createNamedQuery("Users.findAll", Users.class);
            List<Users> users = query.getResultList();
            
            for(Users u: users)
            {
                UserDataModel userDataModel = converter.user2dataModel(u);
                userModels.add(userDataModel);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return userModels;
    }  
}
