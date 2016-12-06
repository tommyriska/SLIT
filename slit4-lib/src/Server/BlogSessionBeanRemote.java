/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import DataModel.BlogDataModel;
import DataModel.BlogPostDataModel;
import DataModel.UserDataModel;
import javax.ejb.Remote;

/**
 *
 * @author Ådne
 */
@Remote
public interface BlogSessionBeanRemote 
{
    void addBlogPost(BlogPostDataModel blogPostDataModel, UserDataModel userDataModel);

    void deleteBlogPost(int blogPostId);

    BlogDataModel getBlogById(UserDataModel udm);
}
