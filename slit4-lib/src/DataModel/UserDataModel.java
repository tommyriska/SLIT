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
public class UserDataModel implements java.io.Serializable
{
    private Integer usersId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer phoneNumber;
    private Integer rights;
    private String title;
    private List<DeliveryDataModel> deliveryList;
    private BlogDataModel blogDataModel;
    private SemesterPlanDataModel semesterPlanDataModel;

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getRights() {
        return rights;
    }

    public void setRights(Integer rights) {
        this.rights = rights;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DeliveryDataModel> getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(List<DeliveryDataModel> deliveryList) {
        this.deliveryList = deliveryList;
    }

    public BlogDataModel getBlogDataModel() {
        return blogDataModel;
    }

    public void setBlogDataModel(BlogDataModel blogDataModel) {
        this.blogDataModel = blogDataModel;
    }

    public SemesterPlanDataModel getSemesterPlanDataModel() {
        return semesterPlanDataModel;
    }

    public void setSemesterPlanDataModel(SemesterPlanDataModel semesterPlanDataModel) {
        this.semesterPlanDataModel = semesterPlanDataModel;
    }
    
    
}
