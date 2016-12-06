/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Adne
 */
public class DeliveryDataModel implements java.io.Serializable
{
    private Integer deliveryId;
    private int status;
    private String dComment;
    private String rComment;
    private Date dateDelivered;
    private Date dateApproved;
    private UserDataModel userDataModel;
    private ModuleDataModel moduleID;
    private List<FileDataModel> fileList;

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getdComment() {
        return dComment;
    }

    public void setdComment(String dComment) {
        this.dComment = dComment;
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

    public List<FileDataModel> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileDataModel> fileList) {
        this.fileList = fileList;
    }

    public ModuleDataModel getModuleID() {
        return moduleID;
    }

    public void setModuleID(ModuleDataModel moduleID) {
        this.moduleID = moduleID;
    }

    public UserDataModel getUserDataModel() {
        return userDataModel;
    }

    public void setUserDataModel(UserDataModel userDataModel) {
        this.userDataModel = userDataModel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getrComment() {
        return rComment;
    }

    public void setrComment(String rComment) {
        this.rComment = rComment;
    }
    
}
