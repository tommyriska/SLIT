package Database;

import Database.ModulePlan;
import Database.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-22T14:27:48")
@StaticMetamodel(SemesterPlan.class)
public class SemesterPlan_ { 

    public static volatile ListAttribute<SemesterPlan, Users> usersList;
    public static volatile ListAttribute<SemesterPlan, ModulePlan> modulePlanList;
    public static volatile SingularAttribute<SemesterPlan, Integer> semesterPlanId;

}