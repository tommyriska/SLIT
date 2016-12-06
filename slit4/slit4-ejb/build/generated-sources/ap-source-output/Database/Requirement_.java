package Database;

import Database.Module;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-22T14:27:48")
@StaticMetamodel(Requirement.class)
public class Requirement_ { 

    public static volatile SingularAttribute<Requirement, String> description;
    public static volatile SingularAttribute<Requirement, Integer> requirementId;
    public static volatile SingularAttribute<Requirement, Module> moduleId;

}