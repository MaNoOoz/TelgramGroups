package com.manooz.telgramgroups.Current_Project;

import android.support.annotation.NonNull;

import com.google.firebase.firestore.Exclude;
import com.manooz.telgramgroups.Current_Project.POJO.Group_Object;

public class GroupId {

    @Exclude
    public String GroupId;

    public <T extends Group_Object> T withId(@NonNull final String id) {
        this.GroupId = id;
        return (T) this;
    }

}
