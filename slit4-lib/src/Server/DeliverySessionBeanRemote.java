/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DataModel.DeliveryDataModel;
import DataModel.UserDataModel;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Adne
 */
@Remote
public interface DeliverySessionBeanRemote 
{

    void addDelivery(DeliveryDataModel deliveryDataModel, UserDataModel userDataModel);

    List<DeliveryDataModel> getDeliveryList();

    void changeStatus(DeliveryDataModel deliveryDataModel, int status);

    void addReviewComment(DeliveryDataModel deliveryDataModel, String comment);
    
}
