package com.manooz.telgramgroups.Current_Project.Interfaces;

import com.manooz.telgramgroups.Current_Project.POJO.Group_Object;


/**
 * Created by Gohar Ali on 12/13/2017.
 */

public interface OnListInteractionListener {

     void onGroupsListInteraction(Group_Object groupObject) ;

//     void onCategoryListInteraction(Category item) ;
//
//     void onAppointmentInteraction(Appointment item, int pos) ;
//
//     void onProviderFavorite(ProviderModel item);
//
//     void onJobItemSelection(JobItem item);
//
//     void onUserMessageSelection(String path, int post);

     void removeItem(int position);
}
