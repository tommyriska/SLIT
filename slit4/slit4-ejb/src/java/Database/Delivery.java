/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Adne
 */
@Entity
@Table(name = "delivery")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Delivery.findAll", query = "SELECT d FROM Delivery d"),
    @NamedQuery(name = "Delivery.findByDeliveryId", query = "SELECT d FROM Delivery d WHERE d.deliveryId = :deliveryId"),
    @NamedQuery(name = "Delivery.findByDeliveryStatus", query = "SELECT d FROM Delivery d WHERE d.deliveryStatus = :deliveryStatus"),
    @NamedQuery(name = "Delivery.findByDeliveryComment", query = "SELECT d FROM Delivery d WHERE d.deliveryComment = :deliveryComment"),
    @NamedQuery(name = "Delivery.findByDateDelivered", query = "SELECT d FROM Delivery d WHERE d.dateDelivered = :dateDelivered"),
    @NamedQuery(name = "Delivery.findByDateApproved", query = "SELECT d FROM Delivery d WHERE d.dateApproved = :dateApproved"),
    @NamedQuery(name = "Delivery.findByReviewComment", query = "SELECT d FROM Delivery d WHERE d.reviewComment = :reviewComment")})
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "delivery_id")
    private Integer deliveryId;
    @Column(name = "delivery_status")
    private Integer deliveryStatus;
    @Size(max = 500)
    @Column(name = "delivery_comment")
    private String deliveryComment;
    @Column(name = "date_delivered")
    @Temporal(TemporalType.DATE)
    private Date dateDelivered;
    @Column(name = "date_approved")
    @Temporal(TemporalType.DATE)
    private Date dateApproved;
    @Size(max = 1000)
    @Column(name = "review_comment")
    private String reviewComment;
    @JoinColumn(name = "module_id", referencedColumnName = "module_id")
    @ManyToOne
    private Module moduleId;
    @JoinColumn(name = "users_id", referencedColumnName = "users_id")
    @ManyToOne
    private Users usersId;
    @OneToMany(mappedBy = "deliveryId")
    private List<DeliveryFile> deliveryFileList;

    public Delivery() {
    }

    public Delivery(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Integer getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Integer deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryComment() {
        return deliveryComment;
    }

    public void setDeliveryComment(String deliveryComment) {
        this.deliveryComment = deliveryComment;
    }

    public Date getDateDelivered() {
        return dateDelivered;
    }

    public void setDateDelivered(Date dateDelivered) {
        this.dateDelivered = dateDelivered;
    }

    public Date getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(Date dateApproved) {
        this.dateApproved = dateApproved;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public Module getModuleId() {
        return moduleId;
    }

    public void setModuleId(Module moduleId) {
        this.moduleId = moduleId;
    }

    public Users getUsersId() {
        return usersId;
    }

    public void setUsersId(Users usersId) {
        this.usersId = usersId;
    }

    @XmlTransient
    public List<DeliveryFile> getDeliveryFileList() {
        return deliveryFileList;
    }

    public void setDeliveryFileList(List<DeliveryFile> deliveryFileList) {
        this.deliveryFileList = deliveryFileList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deliveryId != null ? deliveryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Delivery)) {
            return false;
        }
        Delivery other = (Delivery) object;
        if ((this.deliveryId == null && other.deliveryId != null) || (this.deliveryId != null && !this.deliveryId.equals(other.deliveryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Delivery[ deliveryId=" + deliveryId + " ]";
    }
    
}
