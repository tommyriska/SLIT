/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DIV.Converter;
import DataModel.ModuleDataModel;
import DataModel.RequirementDataModel;
import DataModel.ResourceDataModel;
import Database.Module;
import Database.Requirement;
import Database.Resource;
import Server.ModuleSessionBeanRemote;
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
public class ModuleSessionBean implements ModuleSessionBeanRemote 
{
    @PersistenceContext(unitName="slit4-ejbPU")
    private EntityManager em;
    
    @Override
    public void addModule(ModuleDataModel moduleDataModel) 
    {
        Module module = new Module();
        
        module.setDescription(moduleDataModel.getDescription());
        module.setModuleId(moduleDataModel.getModuleId());
        module.setModuleName(moduleDataModel.getModuleName());
        module.setRights(moduleDataModel.getRights());
        
        List<Resource> resourceList = new ArrayList<Resource>();
        for(ResourceDataModel rdm: moduleDataModel.getResourceList())
        {
            Resource resource = new Resource();
            
            resource.setDescription(rdm.getDescription());
            resource.setResourceId(rdm.getResourceId());
            resource.setModuleId(module);
            
            resourceList.add(resource);
        }
        module.setResourceList(resourceList);
        
        List<Requirement> requirementList = new ArrayList<Requirement>();
        for(RequirementDataModel rdm: moduleDataModel.getRequirementList())
        {
            Requirement requirement = new Requirement();
            
            requirement.setDescription(rdm.getDescription());
            requirement.setRequirementId(rdm.getRequirementId());
            requirement.setModuleId(module);
            
            requirementList.add(requirement);
        } 
        module.setRequirementList(requirementList);
        
        em.persist(module);
        
        for(Resource r: resourceList)
        {
            em.persist(r);
        }
        
        for(Requirement r: requirementList)
        {
            em.persist(r);
        }
    }

    @Override
    public List<ModuleDataModel> getAllModules() 
    {
        Converter converter = new Converter();
        List<ModuleDataModel> dataModels = new ArrayList<ModuleDataModel>();
        
        try
        {
            Query query = em.createNamedQuery("Module.findAll", Module.class);
            List<Module> modules = query.getResultList();
            
            for(Module m: modules)
            {
                ModuleDataModel moduleDataModel = converter.module2DataModel(m);
                dataModels.add(moduleDataModel);
                
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        System.out.println(dataModels.size());
        return dataModels;
    }
}
