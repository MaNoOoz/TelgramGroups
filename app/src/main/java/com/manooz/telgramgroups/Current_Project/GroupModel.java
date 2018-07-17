package com.manooz.telgramgroups.Current_Project;

public class GroupModel {
    String GroupName ,GroupDesc,GroupLink,UserName;

    public GroupModel(String groupName, String groupDesc, String groupLink, String userName) {
        this.GroupName = groupName;
        this.GroupDesc = groupDesc;
        this.GroupLink = groupLink;
        this.UserName = userName;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }

    public String getGroupDesc() {
        return GroupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        GroupDesc = groupDesc;
    }

    public String getGroupLink() {
        return GroupLink;
    }

    public void setGroupLink(String groupLink) {
        GroupLink = groupLink;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
