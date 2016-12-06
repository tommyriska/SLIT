/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;

import DataModel.BlogDataModel;
import DataModel.BlogPostDataModel;
import DataModel.UserDataModel;
import Server.BlogSessionBeanRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Ådne
 */
public class BlogManager 
{

    private BlogSessionBeanRemote lookupBlogSessionBeanRemote() 
    {
        try 
        {
            Context c = new InitialContext();
            //return (BlogSessionBeanRemote) c.lookup("java:comp/env/BlogSessionBean");
            return (BlogSessionBeanRemote) c.lookup("java:global/slit4/slit4-ejb/BlogSessionBean");
        } 
        catch (NamingException ne) 
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    public void addBlogPost(BlogPostDataModel blogPostDataModel, UserDataModel userDataModel)
    {
        lookupBlogSessionBeanRemote().addBlogPost(blogPostDataModel, userDataModel);
    }
    
    public BlogDataModel getBlogById(UserDataModel user)
    {
        BlogDataModel bp = lookupBlogSessionBeanRemote().getBlogById(user);
        return bp;
    }
}
