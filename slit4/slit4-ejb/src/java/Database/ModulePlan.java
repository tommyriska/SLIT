/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adne
 */
@Entity
@Table(name = "module_plan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModulePlan.findAll", query = "SELECT m FROM ModulePlan m"),
    @NamedQuery(name = "ModulePlan.findByModulePlanId", query = "SELECT m FROM ModulePlan m WHERE m.modulePlanId = :modulePlanId"),
    @NamedQuery(name = "ModulePlan.findByPlannedDate", query = "SELECT m FROM ModulePlan m WHERE m.plannedDate = :plannedDate")})
public class ModulePlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "module_plan_id")
    private Integer modulePlanId;
    @Column(name = "planned_date")
    @Temporal(TemporalType.DATE)
    private Date plannedDate;
    @JoinColumn(name = "semester_plan_id", referencedColumnName = "semester_plan_id")
    @ManyToOne
    private SemesterPlan semesterPlanId;
    @JoinColumn(name = "module_id", referencedColumnName = "module_id")
    @ManyToOne
    private Module moduleId;

    public ModulePlan() {
    }

    public ModulePlan(Integer modulePlanId) {
        this.modulePlanId = modulePlanId;
    }

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

    public SemesterPlan getSemesterPlanId() {
        return semesterPlanId;
    }

    public void setSemesterPlanId(SemesterPlan semesterPlanId) {
        this.semesterPlanId = semesterPlanId;
    }

    public Module getModuleId() {
        return moduleId;
    }

    public void setModuleId(Module moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modulePlanId != null ? modulePlanId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModulePlan)) {
            return false;
        }
        ModulePlan other = (ModulePlan) object;
        if ((this.modulePlanId == null && other.modulePlanId != null) || (this.modulePlanId != null && !this.modulePlanId.equals(other.modulePlanId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.ModulePlan[ modulePlanId=" + modulePlanId + " ]";
    }
    
}
