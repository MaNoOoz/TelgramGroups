package com.manooz.telgramgroups.Current_Project.POJO;

import java.util.ArrayList;

public class Catogery {

    private String mCatName,mCatDesc;
    private int mCatPic;

    public Catogery() {
    }

    public Catogery(String mCatName, String mCatDesc, int mCatPic) {
        this.mCatName = mCatName;
        this.mCatDesc = mCatDesc;
        this.mCatPic = mCatPic;
    }

    public String getmCatName() {
        return mCatName;
    }

    public void setmCatName(String mCatName) {
        this.mCatName = mCatName;
    }

    public String getmCatDesc() {
        return mCatDesc;
    }

    public void setmCatDesc(String mCatDesc) {
        this.mCatDesc = mCatDesc;
    }

    public int getmCatPic() {
        return mCatPic;
    }

    public void setmCatPic(int mCatPic) {
        this.mCatPic = mCatPic;
    }
    private static int lastContactId = 0;

    public static ArrayList<Catogery> createContactsList(int numContacts) {
        ArrayList<Catogery> contacts = new ArrayList<Catogery>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new Catogery("Person " ,  " "  , lastContactId));
        }

        return contacts;
    }
}
