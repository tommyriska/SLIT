/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel;

import java.util.Date;

/**
 *
 * @author Adne
 */
public class BlogPostDataModel implements java.io.Serializable
{
    private Integer blogPostId;
    private Date date;
    private String title;
    private String content;

    public Integer getBlogPostId() {
        return blogPostId;
    }

    public void setBlogPostId(Integer blogPostId) {
        this.blogPostId = blogPostId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
