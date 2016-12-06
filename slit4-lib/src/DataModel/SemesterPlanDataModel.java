/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel;

import java.util.List;

/**
 *
 * @author Adne
 */
public class SemesterPlanDataModel implements java.io.Serializable
{
    private Integer semesterPlanId;
    private List<ModulePlanDataModel> modulePlanList;

    public Integer getSemesterPlanId() {
        return semesterPlanId;
    }

    public void setSemesterPlanId(Integer semesterPlanId) {
        this.semesterPlanId = semesterPlanId;
    }

    public List<ModulePlanDataModel> getModulePlanList() {
        return modulePlanList;
    }

    public void setModulePlanList(List<ModulePlanDataModel> modulePlanList) {
        this.modulePlanList = modulePlanList;
    }
    
    
}
