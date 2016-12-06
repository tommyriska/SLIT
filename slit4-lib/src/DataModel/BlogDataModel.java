/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel;

import java.util.List;

/**
 *
 * @author Adne
 */
public class BlogDataModel implements java.io.Serializable
{
    private Integer blogId;
    private List<BlogPostDataModel> blogPostList;

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public List<BlogPostDataModel> getBlogPostList() {
        return blogPostList;
    }

    public void setBlogPostList(List<BlogPostDataModel> blogPostList) {
        this.blogPostList = blogPostList;
    }
    
    
}
