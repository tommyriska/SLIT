package Database;

import Database.Module;
import Database.SemesterPlan;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-22T14:27:48")
@StaticMetamodel(ModulePlan.class)
public class ModulePlan_ { 

    public static volatile SingularAttribute<ModulePlan, Date> plannedDate;
    public static volatile SingularAttribute<ModulePlan, Integer> modulePlanId;
    public static volatile SingularAttribute<ModulePlan, Module> moduleId;
    public static volatile SingularAttribute<ModulePlan, SemesterPlan> semesterPlanId;

}