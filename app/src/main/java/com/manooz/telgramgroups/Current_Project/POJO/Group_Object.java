package com.manooz.telgramgroups.Current_Project.POJO;

import com.google.firebase.database.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;


// Created by MaNoOoz on 1/1/2018.
//
@IgnoreExtraProperties
//https://github.com/AndroidCourseMaterial/MovieQuotes
public class Group_Object extends com.manooz.telgramgroups.Current_Project.GroupId {

    // Object Fields
    public static final String  KEY_TITLE = "title";
    public static final String  KEY_DESCRIPTION = "description";
    public static final String  FIELD_GroupName = "groupName";
    public static final String  FIELD_GroupDesc = "groupDesc";
    public static final String  FIELD_GroupLink = "groupLink";
    public static final String  FIELD_UserName = "UserName";
    public static final String  FIELD_CATEGORY = "categories";
    public static final String  FIELD_NumOfComments = "mNumOfComments";
    public static final String  FIELD_NumOfLiks = "mNumOfLiks";
    public static final String  FIELD_NumOfViews = "mNumOfViews";
    public static final String  FIELD_NumOfRatings = "mNumOfRatings";
    public static final String  FIELD_Ratings = "ratings";


    private String photo;
    private String documentId;
    private String GroupName;
    private String GroupDesc;
    private String GroupLink;
    private String UserName;
    private String city;

    private String categories;
    private double Ratings;
    private int mNumOfRatings;
    private int mNumOfLiks;
    private int mNumOfComments;
    private int mNumOfViews;
    private Date mTimestamp;


    public Group_Object(String photo, String groupName, String groupDesc,
                        String groupLink, String userName, String categories, String city,
                        double ratings, int mNumOfRatings, int mNumOfLiks, int mNumOfComments, int mNumOfViews) {
        this.photo = photo;
        this.GroupName = groupName;
        this.GroupDesc = groupDesc;
        this.GroupLink = groupLink;
        this.UserName = userName;
        this.categories = categories;
        this.city = city;

        this.Ratings = ratings;
        this.mNumOfRatings = mNumOfRatings;
        this.mNumOfLiks = mNumOfLiks;
        this.mNumOfComments = mNumOfComments;
        this.mNumOfViews = mNumOfViews;


    }

    public Group_Object() {

    }



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public int getmNumOfLiks() {
        return mNumOfLiks;
    }

    public void setmNumOfLiks(int mNumOfLiks) {
        this.mNumOfLiks = mNumOfLiks;
    }

    public int getmNumOfComments() {
        return mNumOfComments;
    }

    public void setmNumOfComments(int mNumOfComments) {
        this.mNumOfComments = mNumOfComments;
    }

    public int getmNumOfViews() {
        return mNumOfViews;
    }

    public void setmNumOfViews(int mNumOfViews) {
        this.mNumOfViews = mNumOfViews;
    }

    public Date getmTimestamp() {
        return mTimestamp;
    }

    public void setmTimestamp(Date mTimestamp) {
        this.mTimestamp = mTimestamp;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public double getRatings() {
        return Ratings;
    }

    public void setRatings(double ratings) {
        Ratings = ratings;
    }

    public int getmNumOfRatings() {
        return mNumOfRatings;
    }

    public void setmNumOfRatings(int mNumOfRatings) {
        this.mNumOfRatings = mNumOfRatings;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
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

    @ServerTimestamp
    public Date getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(Date timestamp) {
        mTimestamp = timestamp;
    }
}


