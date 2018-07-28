//package com.manooz.telgramgroups.Current_Project;
//
//import android.os.Parcel;
//import android.os.Parcelable;
//
//public class GroupModel implements Parcelable {
//    private String GroupName,GroupDesc,GroupLink,UserName;
//
//    public GroupModel(String groupName, String groupDesc, String groupLink, String userName) {
//        this.GroupName = groupName;
//        this.GroupDesc = groupDesc;
//        this.GroupLink = groupLink;
//        this.UserName = userName;
//    }
//
//    protected GroupModel(Parcel in) {
//        GroupName = in.readString();
//        GroupDesc = in.readString();
//        GroupLink = in.readString();
//        UserName = in.readString();
//    }
//
//    public static final Creator<GroupModel> CREATOR = new Creator<GroupModel>() {
//        @Override
//        public GroupModel createFromParcel(Parcel in) {
//            return new GroupModel(in);
//        }
//
//        @Override
//        public GroupModel[] newArray(int size) {
//            return new GroupModel[size];
//        }
//    };
//
//    public String getGroupName() {
//        return GroupName;
//    }
//
//    public void setGroupName(String groupName) {
//        GroupName = groupName;
//    }
//
//    public String getGroupDesc() {
//        return GroupDesc;
//    }
//
//    public void setGroupDesc(String groupDesc) {
//        GroupDesc = groupDesc;
//    }
//
//    public String getGroupLink() {
//        return GroupLink;
//    }
//
//    public void setGroupLink(String groupLink) {
//        GroupLink = groupLink;
//    }
//
//    public String getUserName() {
//        return UserName;
//    }
//
//    public void setUserName(String userName) {
//        UserName = userName;
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(GroupName);
//        dest.writeString(GroupDesc);
//        dest.writeString(GroupLink);
//        dest.writeString(UserName);
//    }
//
//
//
//
//}
