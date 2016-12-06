/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DataModel.ModuleDataModel;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Adne
 */
@Remote
public interface ModuleSessionBeanRemote 
{
    void addModule(ModuleDataModel moduleDataModel);
    List<ModuleDataModel> getAllModules();
}
