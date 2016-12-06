package Database;

import Database.Delivery;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-22T14:27:48")
@StaticMetamodel(DeliveryFile.class)
public class DeliveryFile_ { 

    public static volatile SingularAttribute<DeliveryFile, String> filetype;
    public static volatile SingularAttribute<DeliveryFile, Delivery> deliveryId;
    public static volatile SingularAttribute<DeliveryFile, String> filename;
    public static volatile SingularAttribute<DeliveryFile, Integer> deliveryFileId;
    public static volatile SingularAttribute<DeliveryFile, byte[]> content;

}