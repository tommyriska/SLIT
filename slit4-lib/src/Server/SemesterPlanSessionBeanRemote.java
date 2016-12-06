/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DataModel.ModulePlanDataModel;
import DataModel.SemesterPlanDataModel;
import DataModel.UserDataModel;
import javax.ejb.Remote;

/**
 *
 * @author Adne
 */
@Remote
public interface SemesterPlanSessionBeanRemote {

    void addModulePlan(ModulePlanDataModel moulePlanDataModel, UserDataModel userDataModel);

    SemesterPlanDataModel getSemesterPlanById(UserDataModel User);
    
}
