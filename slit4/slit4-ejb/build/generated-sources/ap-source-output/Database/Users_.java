package Database;

import Database.Blog;
import Database.Delivery;
import Database.SemesterPlan;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-22T14:27:48")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile ListAttribute<Users, Delivery> deliveryList;
    public static volatile SingularAttribute<Users, String> firstName;
    public static volatile SingularAttribute<Users, String> lastName;
    public static volatile SingularAttribute<Users, Integer> phoneNumber;
    public static volatile SingularAttribute<Users, Integer> rights;
    public static volatile SingularAttribute<Users, Integer> usersId;
    public static volatile SingularAttribute<Users, Blog> blogId;
    public static volatile SingularAttribute<Users, SemesterPlan> semesterPlanId;
    public static volatile SingularAttribute<Users, String> email;

}