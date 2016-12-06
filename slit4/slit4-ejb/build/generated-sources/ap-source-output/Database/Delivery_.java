package Database;

import Database.DeliveryFile;
import Database.Module;
import Database.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-22T14:27:48")
@StaticMetamodel(Delivery.class)
public class Delivery_ { 

    public static volatile SingularAttribute<Delivery, Integer> deliveryId;
    public static volatile SingularAttribute<Delivery, String> reviewComment;
    public static volatile SingularAttribute<Delivery, Date> dateApproved;
    public static volatile SingularAttribute<Delivery, String> deliveryComment;
    public static volatile SingularAttribute<Delivery, Users> usersId;
    public static volatile SingularAttribute<Delivery, Module> moduleId;
    public static volatile ListAttribute<Delivery, DeliveryFile> deliveryFileList;
    public static volatile SingularAttribute<Delivery, Integer> deliveryStatus;
    public static volatile SingularAttribute<Delivery, Date> dateDelivered;

}