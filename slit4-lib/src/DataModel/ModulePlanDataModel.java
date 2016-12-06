/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel;

import java.util.Date;

/**
 *
 * @author Adne
 */
public class ModulePlanDataModel implements java.io.Serializable
{
    private Integer modulePlanId;
    private Date plannedDate;
    private ModuleDataModel moduleId;

    public Integer getModulePlanId() {
        return modulePlanId;
    }

    public void setModulePlanId(Integer modulePlanId) {
        this.modulePlanId = modulePlanId;
    }

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }

    public ModuleDataModel getModuleId() {
        return moduleId;
    }

    public void setModuleId(ModuleDataModel moduleId) {
        this.moduleId = moduleId;
    }
    
    
}
