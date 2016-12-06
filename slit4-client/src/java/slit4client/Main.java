/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slit4client;

import DataModel.BlogPostDataModel;
import DataModel.DeliveryDataModel;
import DataModel.FileDataModel;
import DataModel.ModuleDataModel;
import DataModel.ModulePlanDataModel;
import DataModel.RequirementDataModel;
import DataModel.ResourceDataModel;
import DataModel.UserDataModel;
import Managers.BlogManager;
import Managers.DeliveryManager;
import Managers.ModuleManager;
import Managers.SemesterPlanManager;
import Managers.UserManager;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import slit4gui.Login;

/**
 *
 * @author Adne
 */
public class Main 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       //Managers
       ModuleManager moduleManager = new ModuleManager();
       UserManager userManager = new UserManager();
       DeliveryManager deliveryManager = new DeliveryManager();
       BlogManager blogManager = new BlogManager();
       SemesterPlanManager semesterPlanManager = new SemesterPlanManager();
       
       //TEST: RETRIEVE ALL MODULES
       boolean testRetrieveModules = false;
       
       if(testRetrieveModules)
       {
            //Retreives modulelist with the ModuleManager and prints all module names
            for(ModuleDataModel mdm: moduleManager.getModulesList())
            {
                System.out.println("\n" +mdm.getModuleName() + ": " + mdm.getDescription());
                System.out.println("\nRessurser: ");
                for(ResourceDataModel rmd: mdm.getResourceList())
                {
                    System.out.println(rmd.getDescription());
                }
                System.out.println("\nLæremål: ");
                for(RequirementDataModel rmd: mdm.getRequirementList())
                {
                    System.out.println(rmd.getDescription());
                }
            }
       }
       
       //TEST: ADD MODULE
       boolean testAddModule = false;
       
       if(testAddModule)
       {
            //Creates a ModuleDataModel
            ModuleDataModel mdm = new ModuleDataModel();

            //Creates lists for resources and requirements that belongs to the ModuleDataModel
            ArrayList<ResourceDataModel> resourceList = new ArrayList<ResourceDataModel>();
            ArrayList<RequirementDataModel> requirementList = new ArrayList<RequirementDataModel>();

            //Creates a ResourcesDataModel and a RequirementDataModel
            ResourceDataModel rdm = new ResourceDataModel();
            rdm.setDescription("Ressurs1modul2");
            RequirementDataModel rdm2 = new RequirementDataModel();
            rdm2.setDescription("Læremål1modul2");

            //Adds ResourceDataModel and RequirementDataModel to the lists
            resourceList.add(rdm);
            requirementList.add(rdm2);

            //Adds the lists to the ModuleDataModel along with other data
            mdm.setRequirementList(requirementList);
            mdm.setResourceList(resourceList);
            mdm.setModuleName("Modul 2");
            mdm.setDescription("Beskrivelser for Modul 2");
            mdm.setRights(3);

            //Uses ModuleManager to add the ModuleDataModel to database
            moduleManager.addModule(mdm);
       }
       
       //TEST: RETRIEVE ALL USERS
       boolean testRetrieveUsers = false;
       
       if(testRetrieveUsers)
       {

            for(UserDataModel udm: userManager.getUsersList())
            {
                System.out.println("\n" + udm.getFirstName() + udm.getLastName() + ": " + udm.getEmail());
                System.out.println("Nummer: " + udm.getPhoneNumber());
                System.out.println("Rettigheter: " + udm.getRights());

                if(udm.getTitle() != null)
                {
                     System.out.println("Tittel: " + udm.getTitle());
                }

                System.out.println("\nSemesterplan: ");
                for(ModulePlanDataModel mpdm: udm.getSemesterPlanDataModel().getModulePlanList())
                {
                    System.out.println(mpdm.getModuleId().getModuleName() + ": " + mpdm.getPlannedDate());
                }

                System.out.println("\nBlogg: ");
                for(BlogPostDataModel bpdm: udm.getBlogDataModel().getBlogPostList())
                {
                    System.out.println("(" + bpdm.getDate() + ") " + bpdm.getTitle() + ": " + bpdm.getContent());
                }

                System.out.println("\nBesvarelser: ");
                for(DeliveryDataModel deliveryDataModel: udm.getDeliveryList())
                {
                    System.out.println("Kønummer: " + deliveryDataModel.getDeliveryId());
                    System.out.println("Modul: " + deliveryDataModel.getModuleID().getModuleName());
                    System.out.println("Filer: ");

                    for(FileDataModel fileDataModel: deliveryDataModel.getFileList())
                    {
                        System.out.println(fileDataModel.getContent());
                    }

                    System.out.println("Kommentar: " + deliveryDataModel.getdComment());
                    System.out.println("Levert: " + deliveryDataModel.getDateDelivered());
                    System.out.print("Status: ");
                    if(deliveryDataModel.getStatus() == 1)
                    {
                        System.out.print("Godkjent (" + deliveryDataModel.getDateApproved() + ")");
                    }
                    else if(deliveryDataModel.getStatus() == 2)
                    {
                        System.out.print("Ikke godkjent");
                    }
                    else
                    {
                        System.out.print("Ikke vurdert");
                    }

                    System.out.println("\n\n");
                }
            }
       }
       
       //TEST: ADD DELIVERY & UPLOAD FILE
       boolean testAddDelivery = false;
       
       if(testAddDelivery)
       {
           List<UserDataModel> userList = userManager.getUsersList();
           UserDataModel userDataModel = userList.get(0);
           
           List<ModuleDataModel> moduleList = moduleManager.getModulesList();
           DeliveryDataModel delivery = new DeliveryDataModel();
           Date deliveredDate = new Date(12, 12, 21);
           Date approvedDate = new Date(11, 11, 11);
           
           //delivery.setDateApproved(approvedDate);
           //delivery.setDateDelivered(deliveredDate);
           delivery.setModuleID(moduleList.get(1));
           delivery.setStatus(0);
           delivery.setdComment("fissefar1");
           
           List<FileDataModel> fileDataModelList = new ArrayList<FileDataModel>();
           FileDataModel fileDataModel = new FileDataModel();
           
           //UPLOAD FILE
           try
           {
                Path path = Paths.get("/Users/Adne/Desktop/testfil.txt");
                byte[] data = Files.readAllBytes(path); 
                fileDataModel.setContent(data);
                fileDataModel.setFilename("testfil");
                fileDataModel.setFiletype(".txt");
           }
           catch(Exception e)
           {
               
           }
           
           fileDataModelList.add(fileDataModel);
           
           delivery.setFileList(fileDataModelList);
           
           deliveryManager.addDelivery(delivery, userDataModel);
       }
       
       
       //TEST: RETRIEVE ALL DELIVERIES
       boolean testRetrieveAllDeliveries = false;
       
       if(testRetrieveAllDeliveries)
       {
            List<DeliveryDataModel> deliveryDataModelList = deliveryManager.getAllDeliveries();

            System.out.println("Besvarelser:\n");
            for(DeliveryDataModel deliveryDataModel: deliveryDataModelList)
            {
                System.out.println("Kønummer: " + deliveryDataModel.getDeliveryId());
                System.out.println("Student: " + deliveryDataModel.getUserDataModel().getFirstName() + " " + deliveryDataModel.getUserDataModel().getLastName());
                System.out.println("Modul: " + deliveryDataModel.getModuleID().getModuleName());
                System.out.println("Filer: ");
                
                for(FileDataModel fileDataModel: deliveryDataModel.getFileList())
                {
                    System.out.println(fileDataModel.getContent());
                }
                
                System.out.println("Kommentar: " + deliveryDataModel.getdComment());
                System.out.println("Levert: " + deliveryDataModel.getDateDelivered());
                System.out.print("Status: ");
                if(deliveryDataModel.getStatus() == 1)
                {
                    System.out.print("Godkjent (" + deliveryDataModel.getDateApproved() + ")");
                }
                else if(deliveryDataModel.getStatus() == 2)
                {
                    System.out.print("Ikke godkjent");
                }
                else
                {
                    System.out.print("Ikke vurdert");
                }
                
                System.out.println("\n\n");
            }
       }
       
       //TEST: GET FIRST DELIVERY
       boolean testGetFirstDelivery = false;
       
       if(testGetFirstDelivery)
       {
            DeliveryDataModel deliveryDataModel = deliveryManager.getFirst();
           
            System.out.println("Kønummer: " + deliveryDataModel.getDeliveryId());
            System.out.println("Student: " + deliveryDataModel.getUserDataModel().getFirstName() + " " + deliveryDataModel.getUserDataModel().getLastName());
            System.out.println("Modul: " + deliveryDataModel.getModuleID().getModuleName());
            System.out.println("Filer: ");
                
            for(FileDataModel fileDataModel: deliveryDataModel.getFileList())
            {
                System.out.println(fileDataModel.getContent());
            }
                
            System.out.println("Kommentar: " + deliveryDataModel.getdComment());
            System.out.println("Levert: " + deliveryDataModel.getDateDelivered());
            System.out.print("Status: ");
            if(deliveryDataModel.getStatus() == 1)
            {
                System.out.print("Godkjent (" + deliveryDataModel.getDateApproved() + ")");
            }
            else if(deliveryDataModel.getStatus() == 2)
            {
                System.out.print("Ikke godkjent");
            }
            else
            {
                System.out.print("Ikke vurdert");
            }
                
            System.out.println("\n\n");
       }
       
       //TEST: GET DELIVERIES BY NAME SEARCH
       boolean testGetDeliveriesNameSearch = false;
       
       if(testGetDeliveriesNameSearch)
       {
           String searchWord = "ne";
           ArrayList<DeliveryDataModel> deliveryDataModelList = deliveryManager.getByNameSearch(searchWord);
           ArrayList<UserDataModel> userDataModelList = new ArrayList<UserDataModel>();
           
           System.out.println("Resultater for '" + searchWord + "':");
           
           for(DeliveryDataModel ddm: deliveryDataModelList)
           {
               UserDataModel deliveryUser = ddm.getUserDataModel();
               
               boolean isInList = false;
               
               for(UserDataModel udm: userDataModelList)
               {
                   if(udm.getUsersId() == deliveryUser.getUsersId())
                   {
                       isInList = true;
                   }
               }
               
               if(isInList == false)
               {
                   userDataModelList.add(deliveryUser);
                   System.out.println(deliveryUser.getFirstName() + " " + deliveryUser.getLastName());
               }
           }
       }
       
       //TEST: GET FILE FROM DELIVERY
       boolean testGetFileFromDelivery = false;
       
       if(testGetFileFromDelivery)
       {
           FileDataModel fileDataModel = deliveryManager.getAllDeliveries().get(4).getFileList().get(0);
           byte[] data = fileDataModel.getContent();
           
           try
           {
                Path path = Paths.get("C:/Users/Ådne/Desktop/" + fileDataModel.getFilename() + "" + fileDataModel.getFiletype());
                Files.write(path, data);
           }
           catch(IOException e){}
       }
       
       //TEST: ADD BLOG POST
       boolean testAddBlogPost = false;
       
       if(testAddBlogPost)
       {
           BlogPostDataModel blogPostDataModel = new BlogPostDataModel();
           UserDataModel userDataModel = userManager.getUsersList().get(3);
           
           blogPostDataModel.setContent("Testinnlegg2 juhuu2");
           blogPostDataModel.setDate(new Date(12, 12, 12));
           blogPostDataModel.setTitle("Testtittel2");
           
           blogManager.addBlogPost(blogPostDataModel, userDataModel);
       }
       
       //TEST: GET USER BY NAME SEARCH
       boolean testGetUserNameSearch = false;
       
       if(testGetUserNameSearch)
       {
           String searchWord = "tom";
           List<UserDataModel> userList = userManager.getUsersByNameSearch(searchWord);
           
           System.out.println("Resultater for '" + searchWord + "'");
           
           for(UserDataModel udm: userList)
           {
               System.out.println(udm.getFirstName() + " " + udm.getLastName());
           }
       }
       
       //TEST: ADD MODULE PLAN
       boolean testAddModulePlan = false;
       
       if(testAddModulePlan)
       {
           UserDataModel userDataModel = userManager.getUsersList().get(2);
           ModuleDataModel moduleDataModel = moduleManager.getModulesList().get(1);
           ModulePlanDataModel modulePlanDataModel = new ModulePlanDataModel();
           
           modulePlanDataModel.setModuleId(moduleDataModel);
           modulePlanDataModel.setPlannedDate(new Date(13, 13, 13));
           
           semesterPlanManager.addModulePlan(modulePlanDataModel, userDataModel);
       }
    }
}
