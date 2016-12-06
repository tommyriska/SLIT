/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DataModel.BlogDataModel;
import DataModel.BlogPostDataModel;
import DataModel.UserDataModel;
import Database.Blog;
import Database.BlogPost;
import Database.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ådne
 */
@Stateless
public class BlogSessionBean implements BlogSessionBeanRemote 
{
    @PersistenceContext(unitName="slit4-ejbPU")
    private EntityManager em;

    @Override
    public void addBlogPost(BlogPostDataModel blogPostDataModel, UserDataModel userDataModel) 
    {
        BlogPost blogPost = new BlogPost();
        
        
        Users user = em.find(Users.class, userDataModel.getUsersId());
        Blog blog = em.find(Blog.class, user.getBlogId().getBlogId());
       
        blogPost.setBlogId(blog);
        blogPost.setBlogPostDate(blogPostDataModel.getDate());
        blogPost.setContent(blogPostDataModel.getContent());
        blogPost.setTitle(blogPostDataModel.getTitle());
        
        blog.getBlogPostList().add(blogPost);
        
        em.persist(blogPost);
    }

    @Override
    public void deleteBlogPost(int blogPostId) 
    {
        BlogPost bp = em.find(BlogPost.class, blogPostId);
        
        em.remove(bp);
    }

    @Override
    public BlogDataModel getBlogById(UserDataModel udm) 
    {
        Blog blog = em.find(Blog.class, udm.getBlogDataModel().getBlogId());
        
        BlogDataModel bdm = new BlogDataModel();
        
        List<BlogPostDataModel> blogPostList = new ArrayList<BlogPostDataModel>();
        
        for(BlogPost bp: blog.getBlogPostList())
        {
            BlogPostDataModel bpdm = new BlogPostDataModel();
            bpdm.setContent(bp.getContent());
            bpdm.setTitle(bp.getTitle());
            bpdm.setDate(bp.getBlogPostDate());
            
            blogPostList.add(bpdm);
        }
        
        bdm.setBlogPostList(blogPostList);
        
        return bdm;
    }
    
    
}
