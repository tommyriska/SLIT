package Database;

import Database.BlogPost;
import Database.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-22T14:27:48")
@StaticMetamodel(Blog.class)
public class Blog_ { 

    public static volatile ListAttribute<Blog, Users> usersList;
    public static volatile ListAttribute<Blog, BlogPost> blogPostList;
    public static volatile SingularAttribute<Blog, Integer> blogId;

}