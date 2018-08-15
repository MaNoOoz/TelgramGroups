package com.manooz.telgramgroups.Current_Project.Utily;

import android.app.Activity;
import android.net.Uri;

import com.manooz.telgramgroups.Current_Project.addGroupActivity;
import com.manooz.telgramgroups.R;

public class mConstants   {








   // General String in All Apps
   public static Uri DeveloperApps = Uri.parse("https://play.google.com/store/apps/dev?id=8389389659889758696");

   // Object Fields
   public static final String  KEY_TITLE = "title";
   public static final String  KEY_DESCRIPTION = "description";
   public static final String  GroupName = "groupName";
   public static final String  GroupDesc = "groupDesc";
   public static final String  GroupLink = "groupLink";
   public static final String  UserName = "userName";
   public static final String  CATEGORY = "categories";
   public static final String  NumOfComments = "mNumOfComments";
   public static final String  NumOfLiks = "mNumOfLiks";
   public static final String  NumOfViews = "mNumOfViews";
   public static final String  NumOfRatings = "mNumOfRatings";
   public static final String  Ratings = "ratings";




   public static final String MAIN_COLLECTION = "Groups";
   public static final String SUP_COLLECTION1 = "Groups";
   public static final String SUP_COLLECTION2 = "SportsGroups";
   public static final String SUP_COLLECTION3 = "SportsGroups";
   public static final String SUP_COLLECTION4 = "SportsGroups";
   public static final String SUP_COLLECTION5 = "SportsGroups";
   public static final String SUP_COLLECTION6 = "SportsGroups";

   public static final String DOCUMENT_KEY = "Groups/Islamic";
   public static final String KEYWORD = "keyword";

   public static final String MAIN_COLLECTION2 = "Groups/Sports/SportsGroups";
   public static final String LOCATION = "geo";
   public static final String COUNTRY = "country";
   public static final String CITY = "city";
   public static final String ZIP_CODE = "zipCode";
   public static final String DISTANCE = "distance";
   public static final String IS_FEATURED = "isFeatured";
   public static final String SUB_CATEGORY = "subCategory";
   public static final String DATA = "data";
   public static final String EMPTY_STRING = "";
   public static final String ISFIRSTTIME = "isFirstTime";
   public static final String ISUSERLOGGEDIN = "isUserLoggedIn";
   public static final String TITLE = "title";
   public static final String ID = "id";
   public static final String LATITUDE = "latitude";
   public static final String LONGITUDE = "longitude";
   public static final String SELECTED_ITEM = "selected";


   public enum Errors {
      CATEGORY_FAILED(1), FEATURED(2), PROVIDER(3), SEARCH(4), COUNTRY_LOAD_FAILED(5), APPOINTMENT_FAILED(6), FAILED(7);
      private int value;

      private Errors(int value) {
         this.value = value;
      }

      public static int value(String s) {
         return Errors.valueOf(s).value;
      }


   }
}