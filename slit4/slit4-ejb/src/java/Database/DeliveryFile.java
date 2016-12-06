/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adne
 */
@Entity
@Table(name = "delivery_file")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeliveryFile.findAll", query = "SELECT d FROM DeliveryFile d"),
    @NamedQuery(name = "DeliveryFile.findByDeliveryFileId", query = "SELECT d FROM DeliveryFile d WHERE d.deliveryFileId = :deliveryFileId"),
    @NamedQuery(name = "DeliveryFile.findByFilename", query = "SELECT d FROM DeliveryFile d WHERE d.filename = :filename"),
    @NamedQuery(name = "DeliveryFile.findByFiletype", query = "SELECT d FROM DeliveryFile d WHERE d.filetype = :filetype")})
public class DeliveryFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "delivery_file_id")
    private Integer deliveryFileId;
    @Lob
    @Column(name = "content")
    private byte[] content;
    @Size(max = 100)
    @Column(name = "filename")
    private String filename;
    @Size(max = 10)
    @Column(name = "filetype")
    private String filetype;
    @JoinColumn(name = "delivery_id", referencedColumnName = "delivery_id")
    @ManyToOne
    private Delivery deliveryId;

    public DeliveryFile() {
    }

    public DeliveryFile(Integer deliveryFileId) {
        this.deliveryFileId = deliveryFileId;
    }

    public Integer getDeliveryFileId() {
        return deliveryFileId;
    }

    public void setDeliveryFileId(Integer deliveryFileId) {
        this.deliveryFileId = deliveryFileId;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public Delivery getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Delivery deliveryId) {
        this.deliveryId = deliveryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deliveryFileId != null ? deliveryFileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeliveryFile)) {
            return false;
        }
        DeliveryFile other = (DeliveryFile) object;
        if ((this.deliveryFileId == null && other.deliveryFileId != null) || (this.deliveryFileId != null && !this.deliveryFileId.equals(other.deliveryFileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.DeliveryFile[ deliveryFileId=" + deliveryFileId + " ]";
    }
    
}
