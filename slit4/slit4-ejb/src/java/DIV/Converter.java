/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DIV;

import DataModel.BlogDataModel;
import DataModel.BlogPostDataModel;
import DataModel.DeliveryDataModel;
import DataModel.FileDataModel;
import DataModel.ModuleDataModel;
import DataModel.ModulePlanDataModel;
import DataModel.RequirementDataModel;
import DataModel.ResourceDataModel;
import DataModel.SemesterPlanDataModel;
import DataModel.UserDataModel;
import Database.BlogPost;
import Database.Delivery;
import Database.DeliveryFile;
import Database.Module;
import Database.ModulePlan;
import Database.Requirement;
import Database.Resource;
import Database.Users;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adne
 */
public class Converter 
{
    public ModuleDataModel module2DataModel(Module module)
    {
        ModuleDataModel moduleDataModel = new ModuleDataModel();
        ArrayList<ResourceDataModel> resourceDataModelList = new ArrayList<ResourceDataModel>();
        ArrayList<RequirementDataModel> requirementDataModelList = new ArrayList<RequirementDataModel>();
        
        
        for(Resource r: module.getResourceList())
        {
            ResourceDataModel resourceDataModel = new ResourceDataModel();
            
            resourceDataModel.setDescription(r.getDescription());
            resourceDataModel.setResourceId(r.getResourceId());
            
            resourceDataModelList.add(resourceDataModel);
        }
        
        for(Requirement r: module.getRequirementList())
        {
            RequirementDataModel requirementDataModel = new RequirementDataModel();
            
            requirementDataModel.setDescription(r.getDescription());
            requirementDataModel.setRequirementId(r.getRequirementId());
            
            requirementDataModelList.add(requirementDataModel);
        }
        
        moduleDataModel.setModuleName(module.getModuleName());
        moduleDataModel.setDescription(module.getDescription());
        moduleDataModel.setModuleId(module.getModuleId());
        moduleDataModel.setRights(module.getRights());
        moduleDataModel.setRequirementList(requirementDataModelList);
        moduleDataModel.setResourceList(resourceDataModelList);
        
        return moduleDataModel;
    }
    
    public UserDataModel user2dataModel(Users user)
    {
        UserDataModel userDataModel = new UserDataModel();
        
        userDataModel.setUsersId(user.getUsersId());
        userDataModel.setEmail(user.getEmail());
        userDataModel.setFirstName(user.getFirstName());
        userDataModel.setLastName(user.getLastName());
        userDataModel.setPhoneNumber(user.getPhoneNumber());
        userDataModel.setRights(user.getRights());
        
        //DELIVERY-LIST
        List<DeliveryDataModel> deliveryDataModelList = new ArrayList<DeliveryDataModel>();
        
        for(Delivery d: user.getDeliveryList())
        {
            //deliveryDataModelList.add(this.delivery2dataModel(d));
            
            DeliveryDataModel deliveryDataModel = new DeliveryDataModel();
            
            deliveryDataModel.setDeliveryId(d.getDeliveryId());
            deliveryDataModel.setDateApproved(d.getDateApproved());
            deliveryDataModel.setDateDelivered(d.getDateDelivered());
            deliveryDataModel.setStatus(d.getDeliveryStatus());
            deliveryDataModel.setdComment(d.getDeliveryComment());
            deliveryDataModel.setModuleID(module2DataModel(d.getModuleId()));
            deliveryDataModel.setrComment(d.getReviewComment());
            
            List<FileDataModel> fileDataModelList = new ArrayList<FileDataModel>();
            
            for(DeliveryFile f: d.getDeliveryFileList())
            {
                FileDataModel fileDataModel = new FileDataModel();
                
                fileDataModel.setFileId(f.getDeliveryFileId());
                fileDataModel.setContent(f.getContent());
                fileDataModel.setFilename(f.getFilename());
                fileDataModel.setFiletype(f.getFiletype());
                
                fileDataModelList.add(fileDataModel);
            }
            
            deliveryDataModelList.add(deliveryDataModel);
            deliveryDataModel.setFileList(fileDataModelList);
        }
        
        
        //BLOG
        BlogDataModel blogDataModel = new BlogDataModel();
        blogDataModel.setBlogId(user.getBlogId().getBlogId());
        
        List<BlogPostDataModel> blogPostDataModelList = new ArrayList<BlogPostDataModel>();
        
        for(BlogPost bp: user.getBlogId().getBlogPostList())
        {
            BlogPostDataModel blogPostDataModel = new BlogPostDataModel();
            blogPostDataModel.setBlogPostId(bp.getBlogPostId());
            blogPostDataModel.setContent(bp.getContent());
            blogPostDataModel.setDate(bp.getBlogPostDate());
            blogPostDataModel.setTitle(bp.getTitle());
            
            blogPostDataModelList.add(blogPostDataModel);
        }
        blogDataModel.setBlogPostList(blogPostDataModelList);
        
        //SEMESTERPLAN
        SemesterPlanDataModel semesterPlanDataModel = new SemesterPlanDataModel();
        semesterPlanDataModel.setSemesterPlanId(user.getSemesterPlanId().getSemesterPlanId());
        
        List<ModulePlanDataModel> modulPlanDataModelList = new ArrayList<ModulePlanDataModel>();
        
        for(ModulePlan mp: user.getSemesterPlanId().getModulePlanList())
        {
            ModulePlanDataModel modulePlanDataModel = new ModulePlanDataModel();
            
            modulePlanDataModel.setModuleId(module2DataModel(mp.getModuleId()));
            modulePlanDataModel.setModulePlanId(mp.getModulePlanId());
            modulePlanDataModel.setPlannedDate(mp.getPlannedDate());
            
            modulPlanDataModelList.add(modulePlanDataModel);
        }
        semesterPlanDataModel.setModulePlanList(modulPlanDataModelList);
        
        userDataModel.setDeliveryList(deliveryDataModelList);
        userDataModel.setBlogDataModel(blogDataModel);
        userDataModel.setSemesterPlanDataModel(semesterPlanDataModel);
        
        return userDataModel;
    }
    
    public DeliveryDataModel delivery2dataModel(Delivery delivery)
    {
        DeliveryDataModel deliveryDataModel = new DeliveryDataModel();
        
        deliveryDataModel.setDateApproved(delivery.getDateApproved());
        deliveryDataModel.setDateDelivered(delivery.getDateDelivered());
        deliveryDataModel.setDeliveryId(delivery.getDeliveryId());
        deliveryDataModel.setModuleID(this.module2DataModel(delivery.getModuleId()));
        deliveryDataModel.setStatus(delivery.getDeliveryStatus());
        deliveryDataModel.setdComment(delivery.getDeliveryComment());
        deliveryDataModel.setUserDataModel(this.user2dataModel(delivery.getUsersId()));
        deliveryDataModel.setrComment(delivery.getReviewComment());
        
        List<FileDataModel> fileDataModelList = new ArrayList<FileDataModel>();
        
        for(DeliveryFile f: delivery.getDeliveryFileList())
        {
            FileDataModel fileDataModel = new FileDataModel();
            
            fileDataModel.setFileId(f.getDeliveryFileId());
            fileDataModel.setContent(f.getContent());
            fileDataModel.setFilename(f.getFilename());
            fileDataModel.setFiletype(f.getFiletype());
            
            fileDataModelList.add(fileDataModel);
        }
        
        deliveryDataModel.setFileList(fileDataModelList);
        
        return deliveryDataModel;
    }
}
