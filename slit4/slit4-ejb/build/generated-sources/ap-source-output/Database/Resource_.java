package Database;

import Database.Module;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-22T14:27:48")
@StaticMetamodel(Resource.class)
public class Resource_ { 

    public static volatile SingularAttribute<Resource, Integer> resourceId;
    public static volatile SingularAttribute<Resource, String> description;
    public static volatile SingularAttribute<Resource, Module> moduleId;

}