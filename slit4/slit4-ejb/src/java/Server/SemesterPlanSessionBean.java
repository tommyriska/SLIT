/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DIV.Converter;
import DataModel.ModulePlanDataModel;
import DataModel.SemesterPlanDataModel;
import DataModel.UserDataModel;
import Database.Module;
import Database.ModulePlan;
import Database.SemesterPlan;
import Database.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Adne
 */
@Stateless
public class SemesterPlanSessionBean implements SemesterPlanSessionBeanRemote 
{
    @PersistenceContext(unitName="slit4-ejbPU")
    private EntityManager em;

    @Override
    public void addModulePlan(ModulePlanDataModel modulePlanDataModel, UserDataModel userDataModel) 
    {
        ModulePlan modulePlan = new ModulePlan();
        Module module = em.find(Module.class, modulePlanDataModel.getModuleId().getModuleId());
        Users user = em.find(Users.class, userDataModel.getUsersId());
        SemesterPlan semesterPlan = user.getSemesterPlanId();
        
        modulePlan.setModuleId(module);
        modulePlan.setPlannedDate(modulePlanDataModel.getPlannedDate());
        modulePlan.setSemesterPlanId(semesterPlan);
        
        semesterPlan.getModulePlanList().add(modulePlan);
        
        em.persist(modulePlan);
    }

    @Override
    public SemesterPlanDataModel getSemesterPlanById(UserDataModel User) 
    {
        SemesterPlan sp = em.find(SemesterPlan.class, User.getSemesterPlanDataModel().getSemesterPlanId());
        
        SemesterPlanDataModel spdm = new SemesterPlanDataModel();
        
        Converter converter = new Converter();
        
        List<ModulePlanDataModel> modulPlanList = new ArrayList<ModulePlanDataModel>();
        
        for(ModulePlan mp: sp.getModulePlanList())
        {
            ModulePlanDataModel mpdm = new ModulePlanDataModel();
            
            mpdm.setModuleId(converter.module2DataModel(mp.getModuleId()));
            mpdm.setPlannedDate(mp.getPlannedDate());
            
            modulPlanList.add(mpdm);
        }
        
        spdm.setModulePlanList(modulPlanList);
        
        return spdm;
    }
    
    
}
