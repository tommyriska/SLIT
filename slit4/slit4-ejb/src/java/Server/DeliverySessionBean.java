/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DIV.Converter;
import DataModel.DeliveryDataModel;
import DataModel.FileDataModel;
import DataModel.UserDataModel;
import Database.Delivery;
import Database.DeliveryFile;
import Database.Module;
import Database.Users;
import java.util.ArrayList;
import java.util.Date;
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
public class DeliverySessionBean implements DeliverySessionBeanRemote 
{
    @PersistenceContext(unitName="slit4-ejbPU")
    private EntityManager em;
    
    @Override
    public void addDelivery(DeliveryDataModel deliveryDataModel, UserDataModel userDataModel)
    {
        Users user = em.find(Users.class, userDataModel.getUsersId());
        Module module = em.find(Module.class, deliveryDataModel.getModuleID().getModuleId());
        
        Delivery delivery = new Delivery();
        List<DeliveryFile> fileList = new ArrayList<DeliveryFile>();
        
        delivery.setDeliveryComment(deliveryDataModel.getdComment());
        delivery.setDateApproved(deliveryDataModel.getDateApproved());
        delivery.setDateDelivered(deliveryDataModel.getDateDelivered());
        delivery.setDeliveryId(deliveryDataModel.getDeliveryId());
        delivery.setDeliveryStatus(deliveryDataModel.getStatus());
        delivery.setModuleId(module);
        delivery.setUsersId(user);
        delivery.setReviewComment(deliveryDataModel.getrComment());
        
        for(FileDataModel fdm: deliveryDataModel.getFileList())
        {
            DeliveryFile file = new DeliveryFile();
            
            file.setContent(fdm.getContent());
            file.setDeliveryId(delivery);
            file.setDeliveryFileId(fdm.getFileId());
            file.setFilename(fdm.getFilename());
            file.setFiletype(fdm.getFiletype());
            
            fileList.add(file);
        }
        
        delivery.setDeliveryFileList(fileList);
        
        user.getDeliveryList().add(delivery);
        
        em.persist(delivery);
        
        for(DeliveryFile f: fileList)
        {
            em.persist(f);
        }
    }

    @Override
    public List<DeliveryDataModel> getDeliveryList() 
    {
        Converter converter = new Converter();
        List<DeliveryDataModel> deliveryDataModelList = new ArrayList<DeliveryDataModel>();
        
        try
        {
            Query query = em.createNamedQuery("Delivery.findAll", Delivery.class);
            List<Delivery> deliveries = query.getResultList();
            
            for(Delivery d: deliveries)
            {
                DeliveryDataModel deliveryDataModel = converter.delivery2dataModel(d);
                deliveryDataModelList.add(deliveryDataModel);
            }
        }
        catch(Exception e)
        {
            
        }
        
        System.out.println(deliveryDataModelList.size());
        return deliveryDataModelList;
    }

    @Override
    public void changeStatus(DeliveryDataModel deliveryDataModel, int status) 
    {
        Delivery delivery = em.find(Delivery.class, deliveryDataModel.getDeliveryId());
        

        delivery.setDeliveryStatus(status);
    
        Date date = new Date();
        delivery.setDateApproved(new java.sql.Date(date.getTime()));

        em.merge(delivery);
    }

    @Override
    public void addReviewComment(DeliveryDataModel deliveryDataModel, String comment) 
    {
        Delivery delivery = em.find(Delivery.class, deliveryDataModel.getDeliveryId());
        
        delivery.setReviewComment(comment);
        
        em.merge(delivery);
    }
    
    
}
