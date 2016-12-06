package Database;

import Database.Delivery;
import Database.ModulePlan;
import Database.Requirement;
import Database.Resource;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-22T14:27:48")
@StaticMetamodel(Module.class)
public class Module_ { 

    public static volatile ListAttribute<Module, Delivery> deliveryList;
    public static volatile SingularAttribute<Module, Integer> rights;
    public static volatile SingularAttribute<Module, String> moduleName;
    public static volatile ListAttribute<Module, ModulePlan> modulePlanList;
    public static volatile SingularAttribute<Module, String> description;
    public static volatile SingularAttribute<Module, Integer> moduleId;
    public static volatile ListAttribute<Module, Requirement> requirementList;
    public static volatile ListAttribute<Module, Resource> resourceList;

}