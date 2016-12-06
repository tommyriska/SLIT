/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adne
 */
@Entity
@Table(name = "blog_post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BlogPost.findAll", query = "SELECT b FROM BlogPost b"),
    @NamedQuery(name = "BlogPost.findByBlogPostId", query = "SELECT b FROM BlogPost b WHERE b.blogPostId = :blogPostId"),
    @NamedQuery(name = "BlogPost.findByBlogPostDate", query = "SELECT b FROM BlogPost b WHERE b.blogPostDate = :blogPostDate"),
    @NamedQuery(name = "BlogPost.findByTitle", query = "SELECT b FROM BlogPost b WHERE b.title = :title"),
    @NamedQuery(name = "BlogPost.findByContent", query = "SELECT b FROM BlogPost b WHERE b.content = :content")})
public class BlogPost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "blog_post_id")
    private Integer blogPostId;
    @Column(name = "blog_post_date")
    @Temporal(TemporalType.DATE)
    private Date blogPostDate;
    @Size(max = 100)
    @Column(name = "title")
    private String title;
    @Size(max = 1000)
    @Column(name = "content")
    private String content;
    @JoinColumn(name = "blog_id", referencedColumnName = "blog_id")
    @ManyToOne
    private Blog blogId;

    public BlogPost() {
    }

    public BlogPost(Integer blogPostId) {
        this.blogPostId = blogPostId;
    }

    public Integer getBlogPostId() {
        return blogPostId;
    }

    public void setBlogPostId(Integer blogPostId) {
        this.blogPostId = blogPostId;
    }

    public Date getBlogPostDate() {
        return blogPostDate;
    }

    public void setBlogPostDate(Date blogPostDate) {
        this.blogPostDate = blogPostDate;
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

    public Blog getBlogId() {
        return blogId;
    }

    public void setBlogId(Blog blogId) {
        this.blogId = blogId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (blogPostId != null ? blogPostId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BlogPost)) {
            return false;
        }
        BlogPost other = (BlogPost) object;
        if ((this.blogPostId == null && other.blogPostId != null) || (this.blogPostId != null && !this.blogPostId.equals(other.blogPostId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.BlogPost[ blogPostId=" + blogPostId + " ]";
    }
    
}
