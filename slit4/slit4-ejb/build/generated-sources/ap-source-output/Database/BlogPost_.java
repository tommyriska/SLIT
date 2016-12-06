package Database;

import Database.Blog;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-22T14:27:48")
@StaticMetamodel(BlogPost.class)
public class BlogPost_ { 

    public static volatile SingularAttribute<BlogPost, Integer> blogPostId;
    public static volatile SingularAttribute<BlogPost, String> title;
    public static volatile SingularAttribute<BlogPost, Blog> blogId;
    public static volatile SingularAttribute<BlogPost, String> content;
    public static volatile SingularAttribute<BlogPost, Date> blogPostDate;

}